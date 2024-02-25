package com.azericard.card.service;

import com.azericard.card.dao.entity.Card;
import com.azericard.card.dao.repository.CardRepository;
import com.azericard.card.exception.ExpiredCardException;
import com.azericard.card.mapper.CardMapper;
import com.azericard.card.model.CardDto;
import com.azericard.card.model.request.CardPayoutRequest;
import com.azericard.card.model.request.SaveNewCardRequest;
import com.azericard.commonlib.exception.InsufficientFundsException;
import com.azericard.commonlib.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;
    private final CardMapper mapper;

    public List<CardDto> getCardsByUserId(String userId) {
        return mapper.toDto(repository.findAllByUserId(userId));
    }

    public void saveNewCard(SaveNewCardRequest request, String userId) {
        if (isExpired(request.getExpireYear(), request.getExpireMonth())) {
            throw new ExpiredCardException("Your credit card has expired. Please update your payment details with a valid card to proceed.");
        }
        repository.save(mapper.toCard(request, userId));
    }

    @Transactional
    public void payoutWithCard(CardPayoutRequest request, String userId) {
        Card card = repository.findByIdAndUserId(request.getCardId(), userId)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found."));
        checkHasEnoughBalance(request, card);
        card.setBalance(card.getBalance().subtract(request.getAmount()));
        repository.save(card);
    }

    private static void checkHasEnoughBalance(CardPayoutRequest request, Card card) {
        if (card.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException(
                    "Your balance is not enough for this transaction.");
        }
    }

    private boolean isExpired(int year, int month) {
        return (year < 24) || (year == 24 && month < LocalDate.now().getMonthValue());
    }
}
