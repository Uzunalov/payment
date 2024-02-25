package com.azericard.card.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column
    private UUID id;

    @Column
    private String userId;

    @NotBlank
    @Column
    private String name;

    @Column
    private BigDecimal balance;

    @Column
    private String last4;

    @Column
    private String number;

    @Column
    private int expireMonth;

    @Column
    private int expireYear;

    @Column
    private String cvv;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    void setIdIfMissing() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
