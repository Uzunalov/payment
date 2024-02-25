package com.azericard.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.azericard.user.config.constants.SecurityConstants;
import com.azericard.user.model.UserAccessTokenDto;
import com.azericard.user.util.DateHelper;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final SecurityConstants securityConstants;

    @SneakyThrows
    public UserAccessTokenDto generateToken(String userId) {
        String token = JWT.create()
                .withSubject("User Details")
                .withClaim("id", userId)
                .withExpiresAt(DateHelper.calculateTokenExpireDate())
                .withIssuedAt(DateHelper.getCurrentTime())
                .withIssuer("azericard")
                .sign(Algorithm.HMAC256(securityConstants.getJwtSecretKey()));

        return UserAccessTokenDto.builder()
                .token(token)
                .build();
    }
}
