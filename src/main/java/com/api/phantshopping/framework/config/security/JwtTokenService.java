package com.api.phantshopping.framework.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService
{

    @Value("${token.secret}")
    private String SECRET_KEY;

    private static final String ISSUER = "phantshopping";

    public String generateToken(UserDetailsImpl user) {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception)
        {
            throw new JWTCreationException("Error generating token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception)
        {
            throw new JWTVerificationException("Invalid or expired token.", exception);
        }
    }

    public boolean validateToken(String token) {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            // Se o token for válido, ele não gera exceção, portanto, retornamos verdadeiro
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception)
        {
            return false;
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(4).toInstant();
    }
}
