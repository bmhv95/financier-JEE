package org.financier.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("GOAL")
public class Goal extends Envelope{
    @CreationTimestamp
    private LocalDate startDate;

    private LocalDate endDate;
}
