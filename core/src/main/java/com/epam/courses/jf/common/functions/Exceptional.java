package com.epam.courses.jf.common.functions;

import com.epam.courses.jf.common.Wrapper;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface Exceptional<T, E extends Throwable> extends Wrapper<Either<T, E>> {

    static <T, E extends Throwable> Exceptional<T, E> wrap(Either<T, E> either) {
        return () -> either;
    }

    static <T, E extends Throwable> Exceptional<T, E> withValue(T value) {
        Either<T, E> left = Either.left(value);
        return () -> left;
    }

    static <T, E extends Throwable> Exceptional<T, E> withException(E exception) {
        Either<T, E> right = Either.right(exception);
        return () -> right;
    }

    default <T1> Exceptional<T1, E> mapValue(Function<T, T1> valueTransformer) {
        return wrap(toSrc().mapLeft(valueTransformer));
    }

    default <E1 extends Throwable> Exceptional<T, E1> mapException(Function<E, E1> exceptionTransformer) {
        return wrap(toSrc().mapRight(exceptionTransformer));
    }

    default <T1, E1 extends Throwable> Exceptional<T1, E1> map(Function<T, T1> valueTransformer,
                                                               Function<E, E1> exceptionTransformer) {
        return wrap(toSrc().map(valueTransformer, exceptionTransformer));
    }

    default T getOrThrow() throws E {
        final Either<T, E> either = toSrc();
        if (either.isLeft())
            return either.left();
        else
            throw either.right();
    }

    default <E1 extends Throwable> T getOrThrowUnchecked() throws E1 {
        return getOrThrow(RuntimeException::new);
    }
    
    default <E1 extends Throwable> T getOrThrow(Function<E, E1> exceptionMapper) throws E1 {
        return mapException(exceptionMapper).getOrThrow();
    }

    default Optional<T> toOptional() {
        return toSrc().optionalLeft();
    }
}
