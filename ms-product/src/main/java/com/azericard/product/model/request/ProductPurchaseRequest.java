package com.azericard.product.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseRequest {

    @Min(1)
    private int quantity;
    @NotBlank
    private String cardId;
}
