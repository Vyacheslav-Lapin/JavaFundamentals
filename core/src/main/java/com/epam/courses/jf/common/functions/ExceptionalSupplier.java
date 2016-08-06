package com.epam.courses.jf.common.functions;

import java.util.Optional;
import java.util.function.Supplier;

@FunctionalInterface
public interface ExceptionalSupplier<T, E extends Throwable> extends Supplier<Exceptional<T, E>> {

    T call() throws E;

    @SuppressWarnings("unchecked")
    @Override
    default Exceptional<T, E> get() {
        try {
            return Exceptional.withValue(call());
        } catch (Throwable e) {
            return Exceptional.withException((E) e);
        }
    }

    default T getOrThrowUnchecked() {
        return get().getOrThrowUnchecked();
    }

    default void executeOrThrowUnchecked() {
        get().getOrThrowUnchecked();
    }

    default Optional<T> getAsOptional() {
        return get().toOptional();
    }

    static <T, E extends Throwable> Exceptional<T, E> call(ExceptionalSupplier<T, E> exceptionalSupplier) {
        return exceptionalSupplier.get();
    }

    static <T, E extends Throwable> T getOrThrowUnchecked(ExceptionalSupplier<T, E> exceptionalSupplier) {
        return call(exceptionalSupplier).getOrThrowUnchecked();
    }

    static <T, E extends Throwable> Supplier<T> toUncheckedSupplier(ExceptionalSupplier<T, E> exceptionalSupplier) {
        return exceptionalSupplier::getOrThrowUnchecked;
    }
}
