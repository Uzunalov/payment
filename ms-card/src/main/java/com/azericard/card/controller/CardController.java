package com.azericard.card.controller;

import static com.azericard.commonlib.constants.CommonConstants.USER_ID_HEADER;
import static com.azericard.commonlib.util.ResponseBuilder.buildResponse;

import com.azericard.card.model.CardDto;
import com.azericard.card.model.request.CardPayoutRequest;
import com.azericard.card.model.request.SaveNewCardRequest;
import com.azericard.card.service.CardService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CardController {
    private final CardService cardService;

    @GetMapping("/cards")
    public ResponseEntity<List<CardDto>> getUserCards(
            @RequestHeader(name = USER_ID_HEADER) String userId) {
        return buildResponse(cardService.getCardsByUserId(userId));
    }

    @PostMapping("/cards")
    public ResponseEntity<HttpStatus> saveNewCard(@RequestBody @Valid SaveNewCardRequest request,
        @RequestHeader(name = USER_ID_HEADER) String userId) {
        cardService.saveNewCard(request, userId);
        return buildResponse(HttpStatus.CREATED);
    }

    @PostMapping("/internal/cards/payout")
    public ResponseEntity<HttpStatus> payoutWithCard(@RequestBody @Valid CardPayoutRequest request,
        @RequestHeader(name = USER_ID_HEADER) String userId) {
        cardService.payoutWithCard(request, userId);
        return buildResponse();
    }
}
