package com.epam.courses.jf.common.functions;

import java.util.function.Supplier;

public interface ExceptionalSupplier<T, E extends Throwable> extends Supplier<Either<T, E>> {

    T take() throws E;

    @SuppressWarnings("unchecked")
    @Override
    default Either<T, E> get() {
        try {
            return Either.left(take());
        } catch (Throwable e) {
            return Either.right((E) e);
        }
    }

    static <T, E extends Throwable> Either<T, E> take(ExceptionalSupplier<T, E> exceptionalSupplier) {
        return exceptionalSupplier.get();
    }
}
