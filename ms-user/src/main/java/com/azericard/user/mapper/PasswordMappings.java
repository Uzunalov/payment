package com.azericard.user.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapping;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "password", qualifiedBy = EncodedMapping.class)
public @interface PasswordMappings {
}
