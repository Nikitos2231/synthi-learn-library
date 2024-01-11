package com.synthilearn.loggingstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    @Bean
    public ResponseTraceFilter responseTraceFilter() {
        return new ResponseTraceFilter();
    }
}
