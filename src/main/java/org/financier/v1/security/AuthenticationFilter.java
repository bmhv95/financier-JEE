package org.financier.v1.security;

import org.financier.v1.entity.Account;
import org.financier.v1.entity.EnumRole;
import org.financier.v1.exception.ResourceNotFoundException;
import org.financier.v1.exception.ResponseBody;
import org.financier.v1.service.AccountService;
import org.financier.v1.service.AuthService;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo info;

    private static final String BEARER = "Bearer ";

    @Inject
    private JwtUtils jwtUtils;

    @Override
    public void filter(ContainerRequestContext request) {
        //@DenyAll in class or method: Abort Always
        if (isDenied()) {
            request.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseBody(Response.Status.FORBIDDEN, "Forbidden", "The resource is forbidden"))
                    .build());
            return;
        }

        if(isAllPermit()){
            return;
        }

        String authHeader = request.getHeaderString("Authorization");
        if (isNotValidJwt(authHeader)) {
            request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ResponseBody(Response.Status.UNAUTHORIZED, "Unauthorized", "Invalid token")).build());
            return;
        }

        String token = authHeader.substring(BEARER.length());

        String email = jwtUtils.getEmailFromToken(token);
        EnumRole role = jwtUtils.getRoleFromToken(token);
        Long id = jwtUtils.getIdFromToken(token);

        SecurityContext sc = new UserSecurityContext(new UserPrincipal(email, role, id));

        request.setSecurityContext(sc);
    }

    private boolean isDenied() {
        DenyAll methodDenyAll = info.getResourceMethod().getAnnotation(DenyAll.class);
        DenyAll classDenyAll = info.getResourceClass().getAnnotation(DenyAll.class);
        return methodDenyAll != null || classDenyAll != null;
    }

    private boolean isAllPermit(){
        PermitAll methodPermitAll = info.getResourceMethod().getAnnotation(PermitAll.class);
        PermitAll classPermitAll = info.getResourceClass().getAnnotation(PermitAll.class);
        return methodPermitAll != null || classPermitAll != null;
    }

    private boolean isNotValidJwt(String header) {
        if (header == null) {
            return true;
        }

        try {
            jwtUtils.validateToken(header.substring(BEARER.length()));
        } catch (Exception e) {
            return true;
        }

        return !header.startsWith(BEARER);
    }
}
