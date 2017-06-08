package com.epam.courses.jf.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.epam.courses.jf.core.JavaPlatform.JavaSE;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface Module {
    int value();

    String title() default ""; // last package part by default

    JavaPlatform platform() default JavaSE;
}
