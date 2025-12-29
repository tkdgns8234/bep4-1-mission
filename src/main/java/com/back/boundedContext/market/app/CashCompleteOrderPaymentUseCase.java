package com.back.boundedContext.market.app;

import com.back.boundedContext.cash.app.CashSupport;
import com.back.boundedContext.cash.domain.CashLog;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.cash.event.CashOrderPaymentFailedEvent;
import com.back.shared.cash.event.CashOrderPaymentSucceededEvent;
import com.back.shared.market.event.MarketOrderPaymentRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCompleteOrderPaymentUseCase {
    private final CashSupport cashSupport;
    private final EventPublisher eventPublisher;

    public void handle(MarketOrderPaymentRequestedEvent event) {
        Wallet customerWallet = cashSupport.findWalletByMemberId(event.getOrderDto().customerId()).get();
        Wallet holdingWallet = cashSupport.findHoldingWallet().get();

        if (event.getPgPaymentAmount() > 0) {
            customerWallet.credit(
                    event.getPgPaymentAmount(),
                    CashLog.EventType.충전__PG결제_토스페이먼츠,
                    "Order",
                    event.getOrderDto().id()
            );
        }

        boolean canPay = customerWallet.getBalance() >= event.getOrderDto().salePrice();

        if (canPay) {
            customerWallet.debit(
                    event.getOrderDto().salePrice(),
                    CashLog.EventType.사용__주문결제,
                    "Order",
                    event.getOrderDto().id()
            );

            holdingWallet.credit(
                    event.getOrderDto().salePrice(),
                    CashLog.EventType.임시보관__주문결제,
                    "Order",
                    event.getOrderDto().id()
            );

            eventPublisher.publish(
                    new CashOrderPaymentSucceededEvent(
                            event.getOrderDto(),
                            event.getPgPaymentAmount()
                    )
            );
        } else {
            eventPublisher.publish(
                    new CashOrderPaymentFailedEvent(
                            "400-1",
                            "충전은 완료했지만 %번 주문을 결제완료처리를 하기에는 예치금이 부족합니다.".formatted(event.getOrderDto().id()),
                            event.getOrderDto(),
                            event.getPgPaymentAmount(),
                            event.getPgPaymentAmount() - customerWallet.getBalance()
                    )
            );
        }
    }
}
