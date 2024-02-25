package com.azericard.payment.mapper;

import com.azericard.commonlib.mapper.EntityMapper;
import com.azericard.payment.dao.entity.Payment;
import com.azericard.payment.model.PaymentDto;
import com.azericard.payment.model.request.PaymentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends EntityMapper<PaymentDto, Payment> {

    @Mapping(target = "totalAmount", expression = "java(request.getTotalAmount())")
    @Mapping(target = "status", constant = "PROCESSING")
    Payment toPayment(PaymentRequest request, String userId);
}
