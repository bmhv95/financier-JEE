package org.financier.v1.rest;

import org.financier.v1.exception.ResourceNotFoundException;
import org.financier.v1.security.JwtUtils;
import org.financier.v1.security.model.JwtRequest;
import org.financier.v1.security.model.JwtResponse;
import org.financier.v1.service.AuthService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@PermitAll
@Path("/auth")
public class AuthResource {
    @Inject
    private AuthService authService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Path("/login")
    public JwtResponse login(JwtRequest request) throws ResourceNotFoundException {
        return authService.authenticate(request.getEmail(), request.getPassword());
    }
}
