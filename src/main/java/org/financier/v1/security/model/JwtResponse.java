package org.financier.v1.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.financier.v1.entity.EnumRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String type;
    private String token;

    private String email;
    private EnumRole role;
    private boolean status;
}
