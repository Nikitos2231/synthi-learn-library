package com.synthilearn.loggingstarter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LoggingConfiguration.class)
public @interface EnableLogging {
}
