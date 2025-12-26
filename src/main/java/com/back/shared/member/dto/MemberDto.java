package com.back.shared.member.dto;

import com.back.boundedContext.member.domain.Member;

import java.time.LocalDateTime;

public record MemberDto(
        Integer id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        String password,
        int activityScore
) {
    public MemberDto(Member member) {
        this(
            member.getId(),
            member.getCreateDate(),
            member.getModifyDate(),
            member.getUsername(),
            member.getNickname(),
            member.getPassword(),
            member.getActivityScore()
        );
    }
}
