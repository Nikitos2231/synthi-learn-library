package com.synthilearn.securestarter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@RequiredArgsConstructor
@ComponentScan("com.synthilearn.securestarter")
public class WebConfiguration implements WebFluxConfigurer {

    private final AuthorizationHeaderResolver authorizationHeaderResolver;

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(authorizationHeaderResolver);
    }
}
