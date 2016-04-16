package com.epam.courses.jf.common.functions;

import java.util.function.Supplier;

public interface ExceptionalSupplier<T, E extends Throwable> extends Supplier<Exceptional<T, E>> {

    T take() throws E;

    @SuppressWarnings("unchecked")
    @Override
    default Exceptional<T, E> get() {
        try {
            return Exceptional.withValue(take());
        } catch (Throwable e) {
            return Exceptional.withException((E) e);
        }
    }

    static <T, E extends Throwable> Exceptional<T, E> take(ExceptionalSupplier<T, E> exceptionalSupplier) {
        return exceptionalSupplier.get();
    }
}
