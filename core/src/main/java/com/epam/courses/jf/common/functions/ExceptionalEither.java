package com.epam.courses.jf.common.functions;

import java.util.function.Function;

public class ExceptionalEither<T, E extends Throwable> extends Either<T, E> {

    private ExceptionalEither(T value, E exception) {
        super(value, exception);
    }

    public <T1 extends Throwable> T getThrows(Function<E, T1> exceptionSupplier) throws T1 {
        if (isLeft()) {
            return left();
        } else {
            throw exceptionSupplier.apply(right());
        }
    }

    public static <T, E extends Throwable> ExceptionalEither<T, E> of(T value) {
        return new ExceptionalEither<>(value, null);
    }

    public static <T, E extends Throwable> ExceptionalEither<T, E> exception(E exception) {
        return new ExceptionalEither<>(null, exception);
    }
}
