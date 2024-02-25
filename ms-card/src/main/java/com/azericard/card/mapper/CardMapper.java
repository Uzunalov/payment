package com.azericard.card.mapper;

import com.azericard.card.dao.entity.Card;
import com.azericard.card.model.CardDto;
import com.azericard.card.model.request.SaveNewCardRequest;
import com.azericard.commonlib.mapper.EntityMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = CardEncoderMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper extends EntityMapper<CardDto, Card> {

    @CvvMappings
    @CardNumberMappings
    @Mapping(target = "last4", expression = "java(request.getNumber().substring(request.getNumber().length() - 4))")
    Card toCard(SaveNewCardRequest request, String userId);
}
