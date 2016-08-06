package com.epam.courses.jf.common.functions;

import java.util.function.Predicate;

@FunctionalInterface
public interface ExceptionalPredicate<T, E extends Throwable> extends Predicate<T> {

    boolean is(T t) throws E;

    @Override
    default boolean test(T t) {
        try {
            return is(t);
        } catch (Throwable e) {
            //noinspection unchecked
            return mapException((E) e);
        }
    }

    default boolean mapException(E e) {
        throw new RuntimeException(e);
    }

    static <T, E extends Throwable> boolean call(T t, ExceptionalPredicate<T, E> exceptionalPredicate) {
        return exceptionalPredicate.test(t);
    }
}