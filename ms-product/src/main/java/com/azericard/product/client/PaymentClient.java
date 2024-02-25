package com.azericard.product.client;

import static com.azericard.commonlib.constants.CommonConstants.USER_ID_HEADER;

import com.azericard.product.client.model.PaymentRequest;
import com.azericard.product.constants.PaymentServiceConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PaymentClient {

    private final WebClient webClient;

    public PaymentClient(PaymentServiceConstants constants, WebClient.Builder builder) {
        webClient = builder.baseUrl(constants.getBaseUrl()).build();
    }

    public void payoutWithCard(PaymentRequest request, String userId) {
        webClient.post()
                .header(USER_ID_HEADER, userId)
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
