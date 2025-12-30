package com.back.boundedContext.payout.in;

import com.back.boundedContext.payout.app.PayoutFacade;
import com.back.boundedContext.payout.domain.policy.PayoutPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class PayoutDataInit {
    private final PayoutDataInit self;
    private final PayoutFacade payoutFacade;

    PayoutDataInit(
            @Lazy PayoutDataInit self,
            PayoutFacade payoutFacade
    ) {
        this.self = self;
        this.payoutFacade = payoutFacade;
    }

    @Bean
    @Order(4)
    ApplicationRunner payoutDataInitApplicationRunner() {
        return args -> {
            self.forceMakePayoutReadyCandidatesItems();
            self.collectPayoutItemsMore();
        };
    }

    @Transactional
    public void forceMakePayoutReadyCandidatesItems() {
        System.out.println(PayoutPolicy.PAYOUT_READY_WAITING_DAYS);

        payoutFacade.findPayoutCandidateItems().forEach(item -> {
            LocalDateTime modifyDate = LocalDateTime.now().minusDays(PayoutPolicy.PAYOUT_READY_WAITING_DAYS + 1);
            item.modifyPaymentDate(modifyDate);
        });
    }

    @Transactional
    public void collectPayoutItemsMore() {
        payoutFacade.collectPayoutItemsMore(4);
        payoutFacade.collectPayoutItemsMore(2);
        payoutFacade.collectPayoutItemsMore(2);
    }
}
