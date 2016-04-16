package com.epam.courses.jf.common.functions;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionalFunction<T, R, E extends Throwable> extends Function<T, Exceptional<R, E>> {

    R get(T t) throws E;

    @Override
    default Exceptional<R, E> apply(T t) {
        try {
            return Exceptional.withValue(get(t));
        } catch (Throwable e) {
            //noinspection unchecked
            return Exceptional.withException((E) e);
        }
    }

    static <T, R, E extends Throwable> Exceptional<R, E> getOrThrow(ExceptionalFunction<T, R, E> exceptionalFunction,
                                                                    T param) {
        return exceptionalFunction.apply(param);
    }
}
