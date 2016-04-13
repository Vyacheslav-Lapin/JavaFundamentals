package com.epam.courses.jf.common.functions;

import java.util.function.Consumer;

public interface ExceptionalConsumer<T, E extends Throwable> extends Consumer<T> {

    void call(T t) throws E;

    @Override
    @SuppressWarnings("unchecked")
    default void accept(T t) {
        try {
            call(t);
        } catch (Throwable e) {
            ifThrowable(t, (E) e);
        }
    }

    default void ifThrowable(T t, E e) {
        ifThrowable(e);
    }

    default void ifThrowable(E e) {
        throw new RuntimeException(e);
    }


    static <E extends Throwable> void call(ExceptionalRunnable<E> exceptionalRunnable) {
        exceptionalRunnable.run();
    }
}
