package com.azericard.product.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterRequest {

    private String name;
    private Integer startPrice;
    private Integer endPrice;
}
