package com.azericard.user.mapper;

import com.azericard.commonlib.mapper.EntityMapper;
import com.azericard.user.dao.entity.User;
import com.azericard.user.model.UserDto;
import com.azericard.user.model.request.UserSignUpRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = PasswordEncoderMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDto, User> {

    @PasswordMappings
    User toEntity(UserSignUpRequest request);
}
