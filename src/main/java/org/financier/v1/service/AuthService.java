package org.financier.v1.service;

import org.financier.v1.entity.Account;
import org.financier.v1.exception.ResourceNotFoundException;
import org.financier.v1.security.JwtUtils;
import org.financier.v1.security.model.JwtRequest;
import org.financier.v1.security.model.JwtResponse;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthService {
    @Inject
    private AccountService accountService;
    @Inject
    private JwtUtils jwtUtils;

    private static final String BEARER = "Bearer";


    public boolean authenticate(JwtRequest request){
        try {
            Account account = accountService.findByEmail(request.getEmail());
            return BCrypt.checkpw(request.getPassword(), account.getPassword());
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    public JwtResponse getResponseToken(JwtRequest request) throws ResourceNotFoundException {
        String email = request.getEmail();
        String token = jwtUtils.generateToken(email);
        return new JwtResponse(BEARER, token, email);
    }
}
