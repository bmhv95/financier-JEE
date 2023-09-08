package org.financier.v1.rest;

import org.financier.v1.exception.ResourceNotFoundException;
import org.financier.v1.rest.model.AccountDTO;
import org.financier.v1.security.JwtUtils;
import org.financier.v1.security.model.JwtRequest;
import org.financier.v1.service.AuthService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@PermitAll
@Path("/auth")
public class AuthResource {
    @Inject
    private AuthService authService;

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response login(JwtRequest request) throws ResourceNotFoundException {
        if(authService.authenticate(request)){
            return Response.ok().entity(authService.getResponseToken(request)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response register(AccountDTO accountDTO) throws ResourceNotFoundException {
        return null;
    }
}
