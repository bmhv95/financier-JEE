package org.financier.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("SPENDING")
public class Spending extends Envelope {
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Period periodType;
    private Long periodCount;
}
