package com.azericard.payment.service;

import com.azericard.payment.client.CardClient;
import com.azericard.payment.client.model.CardPayoutRequest;
import com.azericard.payment.dao.entity.Payment;
import com.azericard.payment.dao.repository.PaymentRepository;
import com.azericard.payment.enums.PaymentStatus;
import com.azericard.payment.mapper.PaymentMapper;
import com.azericard.payment.model.PaymentDto;
import com.azericard.payment.model.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CardClient cardClient;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    public List<PaymentDto> getPayments(String userId) {
        return paymentMapper.toDto(paymentRepository.findAllByUserId(userId));
    }

    public void createPayment(PaymentRequest request, String userId) {
        Payment  payment = paymentRepository.save(paymentMapper.toPayment(request, userId));
        try {
            cardClient.payoutWithCard(CardPayoutRequest.builder()
                    .cardId(request.getCardId())
                    .amount(payment.getTotalAmount())
                    .build(), userId);
        } catch (Exception e) {
            changePaymentStatus(payment, PaymentStatus.FAILED);
            throw e;
        }
        changePaymentStatus(payment, PaymentStatus.CHARGED);
    }

    private void changePaymentStatus(Payment payment, PaymentStatus failed) {
        payment.setStatus(failed);
        paymentRepository.save(payment);
    }
}
