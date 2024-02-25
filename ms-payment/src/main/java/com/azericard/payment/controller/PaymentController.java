package com.azericard.payment.controller;

import static com.azericard.commonlib.constants.CommonConstants.USER_ID_HEADER;
import static com.azericard.commonlib.util.ResponseBuilder.buildResponse;

import com.azericard.payment.model.PaymentDto;
import com.azericard.payment.model.request.PaymentRequest;
import com.azericard.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDto>> getPayments(@RequestHeader(USER_ID_HEADER) String userId) {
        return buildResponse(paymentService.getPayments(userId));
    }

    @PostMapping("/internal/payments")
    public ResponseEntity<HttpStatus> createPayment(@RequestHeader(USER_ID_HEADER) String userId,
                                                    @RequestBody @Valid PaymentRequest request) {
        paymentService.createPayment(request, userId);
        return buildResponse();
    }
}
