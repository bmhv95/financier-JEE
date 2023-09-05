package org.financier.v1.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.financier.v1.config.AppConfigService;
import org.financier.v1.entity.Account;
import org.financier.v1.entity.EnumRole;
import org.financier.v1.exception.ResourceNotFoundException;
import org.financier.v1.service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;

@Stateless
public class JwtUtils {
    private static final String EMAIL = "EMAIL";
    private static final String ROLE = "ROLE";
    private static final String ID = "ID";
    private static final String SECRET_KEY = AppConfigService.getSecretKey();
    private static final String ISSUER = AppConfigService.getIssuer();
    private static final Integer TIME_TO_LIVE = AppConfigService.getTimeToLive();
    @Inject
    private AccountService accountService;
    public String generateToken(String email) throws ResourceNotFoundException {

        Account account = accountService.findByEmail(email);
        EnumRole role = account.getRole();
        Long id = account.getId();

            Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withJWTId(UUID.randomUUID().toString())
                    .withSubject(email)
                    .withClaim(ID, id)
                    .withClaim(EMAIL, email)
                    .withClaim(ROLE, role.name())
                    .withExpiresAt(new Date(System.currentTimeMillis() + TIME_TO_LIVE))
                    .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER).build();
        return verifier.verify(token);
    }

    public String getEmailFromToken(String token){
        DecodedJWT decodedJWT = validateToken(token);
        return decodedJWT.getSubject();
    }

    public Long getIdFromToken(String token){
        DecodedJWT decodedJWT = validateToken(token);
        return decodedJWT.getClaim(ID).asLong();
    }

    public EnumRole getRoleFromToken(String token){
        DecodedJWT decodedJWT = validateToken(token);
        return EnumRole.valueOf(decodedJWT.getClaim(ROLE).asString());
    }
}
