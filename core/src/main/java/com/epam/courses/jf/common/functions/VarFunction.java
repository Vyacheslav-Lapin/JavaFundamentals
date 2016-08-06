package com.epam.courses.jf.common.functions;

@SuppressWarnings("WeakerAccess")
@FunctionalInterface
public interface VarFunction<T, R> {

    @SuppressWarnings("unchecked")
    R apply(T... t);
}