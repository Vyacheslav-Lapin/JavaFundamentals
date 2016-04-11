package com.epam.courses.jf.common.functions;

@FunctionalInterface
public interface ExceptionalRunnable<E extends Throwable> extends Runnable {

    void call() throws E;

    @Override
    @SuppressWarnings("unchecked")
    default void run() {
        try {
            call();
        } catch (Throwable e) {
            ifThrowable((E) e);
        }
    }

    default void ifThrowable(E e) {
        throw new RuntimeException(e);
    }

    static <E extends Throwable> void call(ExceptionalRunnable<E> exceptionalRunnable) {
        exceptionalRunnable.run();
    }
}
