package com.synthilearn.securestarter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WebConfiguration.class)
public @interface EnableTokenResolver {
}
