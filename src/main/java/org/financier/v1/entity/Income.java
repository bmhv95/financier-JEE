package org.financier.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.financier.v1.listener.IncomeListener;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(IncomeListener.class)
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Long id;

    private String name;
    @Column(nullable = false)
    private BigDecimal amount;
    @CreationTimestamp
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Period periodType;
    private Long periodCount;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
