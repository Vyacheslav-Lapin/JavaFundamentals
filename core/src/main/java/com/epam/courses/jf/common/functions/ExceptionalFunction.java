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

    default R getOrThrowUnchecked(T param) {
        return apply(param).getOrThrowUnchecked();
    }

    static <T, R, E extends Throwable> R getOrThrowUnchecked(ExceptionalFunction<T, R, E> exceptionalFunction,
                                                             T param) {
        return exceptionalFunction.apply(param).getOrThrowUnchecked();
    }

    static <T, R, E extends Throwable> Function<T, R> toUncheckedFunction(ExceptionalFunction<T, R, E> exceptionalFunction) {
        return t -> getOrThrowUnchecked(exceptionalFunction, t);
    }

    static <T, R, E extends Exception> ExceptionalSupplier<R, E> carry(ExceptionalFunction<T, R, E> exceptionalFunction,
                                                                       T param) {
        return () -> exceptionalFunction.get(param);
    }
}
