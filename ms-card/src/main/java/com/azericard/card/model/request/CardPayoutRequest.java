package com.azericard.card.model.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardPayoutRequest {

    @NotNull
    private UUID cardId;
    @NotNull
    private BigDecimal amount;
}
