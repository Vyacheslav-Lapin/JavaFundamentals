package com.epam.courses.jf.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for interface`s methods to mark it to behave as private-methods.
 *
 * Private methods in interfaces will be included in Java9, so, when public release will be done and project
 * become Java9-only, it will be strongly-recommended to replace this annotation with {@code private} modifier for all
 * marked methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Private {
}
