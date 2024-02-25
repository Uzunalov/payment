package com.azericard.product.service;

import com.azericard.commonlib.exception.ResourceNotFoundException;
import com.azericard.product.client.PaymentClient;
import com.azericard.product.client.model.PaymentRequest;
import com.azericard.product.dao.entity.Product;
import com.azericard.product.dao.repository.ProductRepository;
import com.azericard.product.dao.specification.ProductSpecification;
import com.azericard.product.mapper.ProductMapper;
import com.azericard.product.model.ProductDto;
import com.azericard.product.model.request.ProductFilterRequest;
import com.azericard.product.model.request.ProductPurchaseRequest;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PaymentClient paymentClient;
    private final ProductMapper mapper;
    private final ProductRepository repository;

    @Transactional
    public Page<ProductDto> getProducts(ProductFilterRequest filterRequest, Pageable pageable) {
        return repository.findAll(ProductSpecification.filterProducts(filterRequest), pageable)
                .map(mapper::toDto);
    }

    @Transactional
    public void purchaseProduct(UUID id, ProductPurchaseRequest request, String userId) {
        Product product = repository.findByIdAndStockGreaterThan(id, request.getQuantity())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        paymentClient.payoutWithCard(PaymentRequest.builder()
                .productId(product.getId().toString())
                .cardId(request.getCardId())
                .price(product.getPrice())
                .quantity(request.getQuantity()).build(), userId);

        product.setStock(product.getStock() - request.getQuantity());
        repository.save(product);
    }
}
