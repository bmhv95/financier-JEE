package org.financier.v1.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRoleAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EnumRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_id")
    private Account account;

    @CreationTimestamp
    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime updatedDate;
}
