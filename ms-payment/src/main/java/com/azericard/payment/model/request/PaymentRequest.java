package com.azericard.payment.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotBlank
    private String cardId;
    @NotBlank
    private String productId;
    @Min(1)
    private int quantity;
    @NotNull
    private BigDecimal price;

    public BigDecimal getTotalAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
