package com.epam.courses.jf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.epam.courses.jf.Platform.JavaSE;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface Module {
    int code();

    String title() default ""; // last package part by default

    Platform platform() default JavaSE;
}
