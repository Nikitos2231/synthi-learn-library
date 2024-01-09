package com.synthilearn.securestarter;

import com.synthilearn.securestarter.services.TokenService;
import com.synthilearn.securestarter.services.impl.TokenServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderResolver implements HandlerMethodArgumentResolver {
    private final TokenService tokenService = new TokenServiceImpl();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AccessToken.class);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
                                        ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        assert authorizationHeader != null;
        Claims claims = tokenService.extractPayload(authorizationHeader.substring(7));

        return Mono.just(new AccessToken(claims));
    }
}
