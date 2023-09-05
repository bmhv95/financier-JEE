package org.financier.v1.service;

import org.financier.v1.entity.Account;
import org.financier.v1.entity.EnumRole;
import org.financier.v1.exception.ResourceNotFoundException;
import org.financier.v1.security.JwtUtils;
import org.financier.v1.security.model.JwtRequest;
import org.financier.v1.security.model.JwtResponse;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;

public class AuthService {
    @Inject
    private AccountService accountService;
    @Inject
    private JwtUtils jwtUtils;

    private static final String BEARER = "Bearer";


    public boolean authenticate(JwtRequest request) throws ResourceNotFoundException {
        Account account = accountService.findByEmail(request.getEmail());
        return BCrypt.checkpw(request.getPassword(), account.getPassword());
    }

    public JwtResponse getResponseToken(JwtRequest request) throws ResourceNotFoundException {
        String email = request.getEmail();
        Account account = accountService.findByEmail(email);
        String token = jwtUtils.generateToken(email);
        EnumRole role = account.getRole();
        boolean status = account.getStatus();
        return new JwtResponse(BEARER, token, email, role, status);
    }

    public void validateToken(String token) {
        jwtUtils.validateToken(token);
    }

    public String getEmailFromToken(String token){
        return jwtUtils.getEmailFromToken(token);
    }

    public String getRoleFromEmail(String email) throws ResourceNotFoundException {
        return accountService.findByEmail(email).getRole().name();
    }
}
