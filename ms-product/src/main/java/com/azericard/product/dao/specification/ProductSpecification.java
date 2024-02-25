package com.azericard.product.dao.specification;

import com.azericard.product.dao.entity.Product;
import com.azericard.product.model.request.ProductFilterRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static final String STOCK = "stock";
    public static final String NAME = "name";
    public static final String PRICE = "price";

    public static Specification<Product> filterProducts(ProductFilterRequest request) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.ge(root.get(STOCK), 1));
            if (StringUtils.hasText(request.getName())) {
                predicates.add(builder.like(builder.lower(root.get(NAME)), getNameFilter(request)));
            }
            if (request.getStartPrice() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(PRICE), request.getStartPrice()));
            }
            if (request.getEndPrice() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get(PRICE), request.getEndPrice()));
            }
            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }

    private static String getNameFilter(ProductFilterRequest request) {
        return "%" + request.getName().toLowerCase() + "%";
    }
}
