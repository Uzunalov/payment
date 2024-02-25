package com.azericard.payment.dao.entity;

import com.azericard.payment.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column
    private UUID id;

    @Column
    private String userId;

    @Column
    private String cardId;

    @Column
    private String productId;

    @Column
    private int quantity;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column
    private PaymentStatus status;

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
