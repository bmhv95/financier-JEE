package org.financier.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.financier.v1.listener.TransactionListener;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(TransactionListener.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;
    private String name;
    private String comment;

    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "envelope_id", nullable = false)
    private Envelope envelope;
}
