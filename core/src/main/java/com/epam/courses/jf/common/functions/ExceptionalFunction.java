package com.epam.courses.jf.common.functions;

import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

@FunctionalInterface
public interface ExceptionalFunction<T, R, E extends Throwable> extends Function<T, Optional<R>> {

    R get(T t) throws E;

    @Override
    @SuppressWarnings("unchecked")
    default Optional<R> apply(T t) {
        try {
            return ofNullable(get(t));
        } catch (Throwable e) {
            return ifThrowable( (E) e, t);
        }
    }

    default Optional<R> ifThrowable(E e, T t) {
        return ifThrowable(e);
    }

    default Optional<R> ifThrowable(E e) {
        throw new RuntimeException(e);
    }

    static <T, R> Optional<R> get(ExceptionalFunction<T, R, ?> exceptionalFunction, T param) {
        return exceptionalFunction.apply(param);
    }
}
