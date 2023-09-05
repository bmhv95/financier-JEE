package org.financier.v1.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.financier.v1.entity.EnumRole;

import java.security.Principal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements Principal {
    private String email;
    private EnumRole role;
    private Long id;

    @Override
    public String getName() {
        return email;
    }

}
