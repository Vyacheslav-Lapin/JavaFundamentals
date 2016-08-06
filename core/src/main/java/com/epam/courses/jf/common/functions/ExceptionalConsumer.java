package com.epam.courses.jf.common.functions;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ExceptionalConsumer<T, E extends Throwable> extends Consumer<T> {

    void call(T t) throws E;

    @Override
    default void accept(T t) {
        try {
            call(t);
        } catch (Throwable e) {
            //noinspection unchecked
            ifThrowable((E) e);
        }
    }

    default void ifThrowable(E e) {
        throw new RuntimeException(e);
    }

    static <T, E extends Throwable> Consumer<T> toUncheckedConsumer(ExceptionalConsumer<T, E> exceptionalConsumer) {
        return exceptionalConsumer;
    }

    @SuppressWarnings("unused")
    static <T, E extends Throwable> void call(ExceptionalConsumer<T, E> exceptionalConsumer, T param) {
        call(exceptionalConsumer, param, RuntimeException::new);
    }

    static <T, E extends Throwable, E1 extends Throwable> void call(ExceptionalConsumer<T, E> exceptionalConsumer,
                                                                    T param,
                                                                    Function<E, E1> exceptionTransformer) throws E1 {
        try {
            exceptionalConsumer.call(param);
        } catch (Throwable e) {
            //noinspection unchecked
            throw exceptionTransformer.apply((E) e);
        }
    }
}
