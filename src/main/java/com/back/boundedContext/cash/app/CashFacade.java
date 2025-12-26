package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashMemberCommandUseCase cashMemberCommandUseCase;

    public CashMember syncMember(MemberDto memberDto) {
        CashMember cashMember = new CashMember(
                memberDto.id(),
                memberDto.createDate(),
                memberDto.modifyDate(),
                memberDto.username(),
                "",
                memberDto.nickname(),
                memberDto.activityScore()
        );

        return cashMemberCommandUseCase.saveCashMember(cashMember);
    }
}
