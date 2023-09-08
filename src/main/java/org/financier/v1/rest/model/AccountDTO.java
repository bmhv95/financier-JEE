package org.financier.v1.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.financier.v1.entity.EnumRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Long id;

    @NotBlank
    private String password;

    private String accountName;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @NotNull
    private String email;

    private LocalDate createDate;
    private LocalDate updateDate;

    private EnumRole role;
    private Boolean status;
}
