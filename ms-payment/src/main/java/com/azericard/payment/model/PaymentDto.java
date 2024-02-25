package com.azericard.payment.model;

import com.azericard.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private String userId;
    private String cardId;
    private String productId;
    private BigDecimal totalAmount;
    private PaymentStatus status;
    private LocalDateTime createdAt;
}
