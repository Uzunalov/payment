package com.azericard.payment.client;

import com.azericard.payment.client.model.CardPayoutRequest;
import com.azericard.payment.constants.CardServiceConstants;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CardClient {

    private final CardServiceConstants constants;
    private final WebClient webClient;

    public CardClient(CardServiceConstants constants, WebClient.Builder builder) {
        this.constants = constants;
        this.webClient = builder.baseUrl(constants.getBaseUrl()).build();
    }

    @SneakyThrows
    public void payoutWithCard(CardPayoutRequest request, String userId) {
        webClient.post()
                .uri(constants.getPayoutPath())
                .header("X-User-Id", userId)
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
