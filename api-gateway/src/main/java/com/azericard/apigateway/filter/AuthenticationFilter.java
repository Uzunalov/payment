package com.azericard.apigateway.filter;

import com.azericard.apigateway.security.TokenDetails;
import com.azericard.apigateway.security.TokenProvider;
import com.azericard.apigateway.service.RedisService;
import com.azericard.commonlib.exception.AuthException;
import java.time.Instant;
import java.util.Date;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RedisService redisService;
    private final RouteValidator validator;
    private final TokenProvider tokenProvider;

    public AuthenticationFilter(RedisService redisService, RouteValidator validator, TokenProvider tokenProvider) {
        super(Config.class);
        this.redisService = redisService;
        this.validator = validator;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                String authHeader = getAuthHeader(exchange);
                String userId = validateAndGetUserId(authHeader);
                verifyUserSession(userId);
                ServerHttpRequest modifiedRequest = addUserIdHeader(exchange, userId);
                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            }
            return chain.filter(exchange);
        };
    }

    private String getAuthHeader(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }

    private String validateAndGetUserId(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AuthException("Authentication token is invalid.");
        }
        String token = authHeader.substring(7);
        TokenDetails tokenDetails = tokenProvider.getTokenDetails(token);
        if (isExpired(tokenDetails.getExpireDate())) {
            throw new AuthException("Your session is expired.");
        }

        return tokenDetails.getUserId();
    }

    private void verifyUserSession(String userId) {
        if (!redisService.read(userId)) {
            throw new AuthException("Invalid or expired session.");
        }
    }

    private ServerHttpRequest addUserIdHeader(ServerWebExchange exchange, String userId) {
        return exchange.getRequest().mutate()
                .header("X-User-Id", userId)
                .build();
    }

    private boolean isExpired(Date expireDate) {
        return expireDate.getTime() < Date.from(Instant.now()).getTime();
    }

    public static class Config {
    }
}
