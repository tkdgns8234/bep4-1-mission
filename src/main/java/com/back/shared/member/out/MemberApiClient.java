package com.back.shared.member.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class MemberApiClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8080/member/api/v1/")
            .build();

    public String getRandomSecureTip() {
        return restClient.get()
                .uri("members/randomSecureTip")
                .retrieve()
                .body(String.class);
    }
}
