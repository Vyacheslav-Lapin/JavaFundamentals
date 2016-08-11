package com.epam.courses.jf.common.functions;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Either<L, R> {

    private final L LEFT;
    private final R RIGHT;

    private Either(L left, R right) {
        if (left == null) {
            Objects.requireNonNull(right, "Either should not be empty.");
        }

        LEFT = left;
        RIGHT = right;
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    public boolean isLeft() {
        return LEFT != null;
    }

    public boolean isRight() {
        return RIGHT != null;
    }

    public Either<L, R> peekLeft(Consumer<L> leftConsumer) {
        if (isLeft()) leftConsumer.accept(LEFT);
        return this;
    }

    public Either<L, R> peekRight(Consumer<R> rightConsumer) {
        if (isRight()) rightConsumer.accept(RIGHT);
        return this;
    }

    public Either<L, R> peek(Consumer<L> leftConsumer, Consumer<R> rightConsumer) {
        if (isLeft())
            leftConsumer.accept(LEFT);
        else
            rightConsumer.accept(RIGHT);

        return this;
    }

    public L left() {
//        requireNonNull(LEFT);
        return LEFT;
    }

    public R right() {
//        requireNonNull(RIGHT);
        return RIGHT;
    }

    @SuppressWarnings("unchecked")
    public <L1> Either<L1, R> mapLeft(Function<L, L1> leftFunction) {
        return isLeft() ? left(leftFunction.apply(LEFT)) : (Either<L1, R>) this;
    }

    @SuppressWarnings("unchecked")
    public <R1> Either<L, R1> mapRight(Function<R, R1> rightFunction) {
        return isRight() ? right(rightFunction.apply(RIGHT)) : (Either<L, R1>) this;
    }

    public <L1, R1> Either<L1, R1> map(Function<L, L1> leftFunction, Function<R, R1> rightFunction) {
        return isLeft()
                ? left(leftFunction.apply(LEFT))
                : right(rightFunction.apply(RIGHT));
    }

    public void apply(Consumer<L> leftConsumer, Consumer<R> rightConsumer) {
        if (isLeft())
            leftConsumer.accept(LEFT);
        else
            rightConsumer.accept(RIGHT);
    }

    public Either<R, L> swap() {
        return new Either<>(RIGHT, LEFT);
    }

    public Optional<L> optionalLeft() {
        return Optional.ofNullable(LEFT);
    }

    public Optional<R> optionalRight() {
        return Optional.ofNullable(RIGHT);
    }

    public <T> T fold(Function<L, T> leftFunction, Function<R, T> rightFunction) {
        return isLeft()
                ? leftFunction.apply(LEFT)
                : rightFunction.apply(RIGHT);
    }
}
