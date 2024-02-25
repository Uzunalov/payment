package com.azericard.card.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapping;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "number", qualifiedBy = EncodedMapping.class)
public @interface CardNumberMappings {

}
