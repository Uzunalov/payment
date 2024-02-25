package com.azericard.card.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapping;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "cvv", qualifiedBy = EncodedMapping.class)
public @interface CvvMappings {
}
