package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashMemberCommandUseCase cashMemberCommandUseCase;
    private final WalletRepository walletRepository;
    private final CashMemberRepository cashMemberRepository;

    @Transactional
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

    public Wallet createWallet(CashMember member) {
        Wallet wallet = new Wallet(member);

        return walletRepository.save(wallet);
    }

    public Optional<CashMember> findMemberByUsername(String username) {
        return cashMemberRepository.findByUsername(username);
    }

    public Optional<Wallet> findWalletByMember(CashMember cashMember) {
        return walletRepository.findWalletByMember(cashMember);
    }
}
