package org.financier.v1.security;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Priority(Priorities.AUTHORIZATION)
@Provider
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private SecurityContext sc;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

    }
}
