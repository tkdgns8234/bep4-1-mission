package com.back.boundedContext.market.app;

import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketFacade {
    private final MarketSyncMemberUseCase marketSyncMemberUseCase;

    public void syncMember(MemberDto member) {
        marketSyncMemberUseCase.syncMember(member);
    }
}
