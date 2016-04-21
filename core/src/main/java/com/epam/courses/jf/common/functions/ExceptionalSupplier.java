package com.epam.courses.jf.common.functions;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

@FunctionalInterface
public interface ExceptionalSupplier<T, E extends Exception> extends Supplier<Exceptional<T, E>>, Callable<T> {

    @Override
    T call() throws E;

    @SuppressWarnings("unchecked")
    @Override
    default Exceptional<T, E> get() {
        try {
            return Exceptional.withValue(call());
        } catch (Exception e) {
            return Exceptional.withException((E) e);
        }
    }

    static <T, E extends Exception> Exceptional<T, E> call(ExceptionalSupplier<T, E> exceptionalSupplier) {
        return exceptionalSupplier.get();
    }
}
