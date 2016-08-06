package com.epam.courses.jf.common.functions;

import java.util.Arrays;
import java.util.function.Function;

@FunctionalInterface
public interface ExceptionalRunnable<E extends Throwable> extends Runnable {

    void call() throws E;

    @Override
    default void run() {
        try {
            call();
        } catch (Throwable e) {
            //noinspection unchecked
            ifThrowable((E) e);
        }
    }

    default void ifThrowable(E e) {
        throw new RuntimeException(e);
    }

    @SafeVarargs
    static <E extends Throwable> void run(ExceptionalRunnable<E>... exceptionalRunnables) {
        Arrays.stream(exceptionalRunnables)
                .forEach(exceptionalRunnable -> run(exceptionalRunnable, RuntimeException::new));
    }

    static <E extends Throwable, E1 extends Throwable> void run(ExceptionalRunnable<E> exceptionalRunnable,
                                                                Function<E, E1> exceptionTransformer) throws E1 {
        try {
            exceptionalRunnable.call();
        } catch (Throwable e) {
            //noinspection unchecked
            throw exceptionTransformer.apply((E) e);
        }
    }
}
