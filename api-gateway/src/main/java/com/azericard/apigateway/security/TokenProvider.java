package com.azericard.apigateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.azericard.apigateway.config.constants.SecurityConstants;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final SecurityConstants securityConstants;

    public TokenDetails getTokenDetails(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(securityConstants.getJwtSecretKey()))
                .withSubject("User Details")
                .withIssuer("azericard")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return TokenDetails.builder()
                .userId(jwt.getClaim("id").asString())
                .expireDate(new Date(jwt.getClaim("exp").asLong() * 1000)).build();
    }
}
