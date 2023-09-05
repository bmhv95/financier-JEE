package org.financier.v1.security;

import lombok.RequiredArgsConstructor;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@RequiredArgsConstructor
public class UserSecurityContext implements SecurityContext {
    private final UserPrincipal user;

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return s.equals(user.getRole().name());
    }

    public boolean isUserId(Long id){
        return id.equals(user.getId());
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
