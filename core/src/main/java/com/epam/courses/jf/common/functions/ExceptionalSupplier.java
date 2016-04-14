package com.epam.courses.jf.common.functions;

import java.util.function.Supplier;

public interface ExceptionalSupplier<T, E extends Throwable> extends Supplier<ExceptionalEither<T, E>> {

    T take() throws E;

    @SuppressWarnings("unchecked")
    @Override
    default ExceptionalEither<T, E> get() {
        try {
            return ExceptionalEither.of(take());
        } catch (Throwable e) {
            return ExceptionalEither.exception((E) e);
        }
    }

    static <T, E extends Throwable> ExceptionalEither<T, E> take(ExceptionalSupplier<T, E> exceptionalSupplier) {
        return exceptionalSupplier.get();
    }
}
