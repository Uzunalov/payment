package com.azericard.payment.client.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardPayoutRequest {

    @NotNull
    private String cardId;
    @NotNull
    private BigDecimal amount;
}
