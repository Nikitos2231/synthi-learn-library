package com.synthilearn.loggingstarter;

import io.opentelemetry.api.trace.Span;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class ResponseTraceFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(serverWebExchange);
        Span span = Span.current();
        return webFilterChain.filter(bodyCaptureExchange).doOnSuccess((se) -> {
            span.setAttribute("request", bodyCaptureExchange.getRequest().getFullBody());
            span.setAttribute("response", bodyCaptureExchange.getResponse().getFullBody());
        });
    }
}
