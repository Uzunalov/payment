package com.azericard.product.controller;

import static com.azericard.commonlib.constants.CommonConstants.USER_ID_HEADER;
import static com.azericard.commonlib.util.ResponseBuilder.buildResponse;

import com.azericard.product.model.ProductDto;
import com.azericard.product.model.request.ProductFilterRequest;
import com.azericard.product.model.request.ProductPurchaseRequest;
import com.azericard.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/public/products")
    public ResponseEntity<Page<ProductDto>> getProducts(Pageable pageable,
            ProductFilterRequest filterRequest) {
        return buildResponse(productService.getProducts(filterRequest, pageable));
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<HttpStatus> purchaseProduct(@RequestHeader(USER_ID_HEADER) String userId,
            @PathVariable UUID id,
            @Valid @RequestBody ProductPurchaseRequest request) {
        productService.purchaseProduct(id, request, userId);
        return buildResponse();
    }
}
