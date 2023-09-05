package org.financier.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.financier.v1.listener.AllocationListener;
import org.hibernate.annotations.CreationTimestamp;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AllocationListener.class)
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal amount;

    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "envelope_id")
    private Envelope envelope;

    private Period periodType;
    private Long periodCount;
}
