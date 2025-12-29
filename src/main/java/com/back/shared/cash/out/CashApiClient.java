package com.back.shared.cash.out;

import com.back.shared.cash.dto.WalletDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CashApiClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8080/api/v1/cash")
            .build();

    public WalletDto getItemByMemberId(int memberId) {
        return restClient.get()
                .uri("/wallets/by-member/" + memberId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public long getBalanceByHolderId(int memberId) {
        WalletDto walletDto = getItemByMemberId(memberId);
        return walletDto.getBalance();
    }
}