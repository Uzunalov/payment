package com.azericard.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    public static final List<String> publicEndpoints = List.of(
            "/api/v1/public"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> publicEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().startsWith(uri));

}