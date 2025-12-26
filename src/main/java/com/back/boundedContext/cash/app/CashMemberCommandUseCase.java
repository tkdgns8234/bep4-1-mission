package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashMemberCommandUseCase {
    private final CashMemberRepository cashMemberRepository;

    public CashMember saveCashMember(CashMember cashMember) {
        return cashMemberRepository.save(cashMember);
    }
}
