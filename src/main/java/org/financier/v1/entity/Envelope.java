package org.financier.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@NoArgsConstructor
@AllArgsConstructor
public abstract class Envelope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(nullable = false)
    private BigDecimal budget;

    @Column(nullable = false)
    private BigDecimal spent;

    @Column(nullable = false)
    private BigDecimal allocated;

    @CreationTimestamp
    private LocalDate createdDate;

    @UpdateTimestamp
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "acc_id")
    private Account account;

    @OneToMany(mappedBy = "envelope", cascade = CascadeType.REMOVE)
    private List<Transaction> transactions;

    private boolean isActive;
}
