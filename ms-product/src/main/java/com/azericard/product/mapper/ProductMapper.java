package com.azericard.product.mapper;

import com.azericard.commonlib.mapper.EntityMapper;
import com.azericard.product.dao.entity.Product;
import com.azericard.product.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {

}
