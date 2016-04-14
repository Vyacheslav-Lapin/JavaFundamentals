package com.epam.courses.jf.common.functions;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Either<L, R> {

    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    public L left() {
        if (isRight())
            throw new RuntimeException("\"left\" is null");
        return left;
    }

    public R right() {
        if (isLeft())
            throw new RuntimeException("\"right\" is null");
        return right;
    }

    public boolean isLeft() {
        return right == null;
    }

    public boolean isRight() {
        return left == null;
    }

    public Either<L, R> peekLeft(Consumer<L> leftConsumer) {
        if (isLeft())
            leftConsumer.accept(left);
        return this;
    }

    public Either<L, R> peekRight(Consumer<R> rightConsumer) {
        if (isRight())
            rightConsumer.accept(right);
        return this;
    }

    public <L1> Either<L1, R> mapLeft(Function<L, L1> leftFunction) {
        Objects.requireNonNull(leftFunction);
        return new Either<>(isLeft() ? leftFunction.apply(left): null, right);
    }

    public <R1> Either<L, R1> mapRight(Function<R, R1> rightFunction) {
        Objects.requireNonNull(rightFunction);
        return new Either<>(left, isRight() ? rightFunction.apply(right): null);
    }

    public <L1, R1> Either<L1, R1> map(Function<L, L1> leftFunction, Function<R, R1> rightFunction) {
        Objects.requireNonNull(leftFunction);
        Objects.requireNonNull(rightFunction);
        return isLeft()
                ? new Either<>(leftFunction.apply(left), null)
                : new Either<>(null, rightFunction.apply(right));
    }

    public Either<R, L> swap() {
        return new Either<>(right, left);
    }

    public void apply(Consumer<L> leftConsumer, Consumer<R> rightConsumer) {
        if (isLeft())
            leftConsumer.accept(left);
        else
            rightConsumer.accept(right);
    }
}
