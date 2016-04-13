package com.epam.courses.jf.common.functions;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionalFunction<T, R, E extends Throwable> extends Function<T, Either<R, E>> {

    R get(T t) throws E;

    @Override
    @SuppressWarnings("unchecked")
    default Either<R, E> apply(T t) {
        try {
            return Either.left(get(t));
        } catch (Throwable e) {
            return Either.right((E) e);
        }
    }

    static <T, R, E extends Throwable> Either<R, E> get(ExceptionalFunction<T, R, E> exceptionalFunction, T param) {
        return exceptionalFunction.apply(param);
    }
}
