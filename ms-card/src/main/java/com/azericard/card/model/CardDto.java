package com.azericard.card.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private UUID id;
    private String name;
    private BigDecimal balance;
    private String last4;
    private int expireYear;
    private int expireMonth;
    private LocalDateTime createdAt;
}
