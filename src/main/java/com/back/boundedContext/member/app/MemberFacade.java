package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.domain.MemberPolicy;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.global.rsData.RsData;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberJoinUseCase memberJoinUseCase;
    private final MemberQueryUseCase memberQueryUseCase;
    private final MemberModifyUseCase memberModifyUseCase;
    private final MemberRepository memberRepository;
    private final MemberPolicy memberPolicy;

    @Transactional
    public RsData<Member> join(String username, String password, String nickname) {
        return memberJoinUseCase.join(username, password, nickname);
    }

    @Transactional
    public RsData<Member> modify(MemberDto memberDto) {
        Member member = memberModifyUseCase.modify(memberDto.id(), memberDto.username(), memberDto.password(), memberDto.nickname());

        return new RsData<>("201", "%d번 회원이 수정되었습니다.".formatted(member.getId()), member);
    }

    @Transactional(readOnly = true)
    public long count() {
        return memberQueryUseCase.count();
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(int id) {
        return memberQueryUseCase.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberQueryUseCase.findByUsername(username);
    }

    public String getRandomSecureTip() {
        int pwdExpiredDays = memberPolicy.getNeedToChangePasswordDays();
        return "비밀번호의 유효기간은 %d 일 입니다.".formatted(pwdExpiredDays);
    }
}
