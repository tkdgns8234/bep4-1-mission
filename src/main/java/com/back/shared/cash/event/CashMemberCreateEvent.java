package com.back.shared.cash.event;

import com.back.boundedContext.cash.domain.CashMember;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CashMemberCreateEvent {
    private final CashMember cashMember;
}
