package com.azericard.card.dao.repository;

import com.azericard.card.dao.entity.Card;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, UUID> {

    List<Card> findAllByUserId(String userId);

    Optional<Card> findByIdAndUserId(UUID id, String userId);
}
