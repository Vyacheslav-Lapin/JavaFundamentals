package com.epam.courses.jf.common.functions;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Either<L, R> {

    private L left;
    private R right;

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
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public Either<L, R> ifLeft(Consumer<L> leftConsumer) {
        leftConsumer.accept(left);
        return this;
    }

    public Either<L, R> ifRight(Consumer<R> rightConsumer) {
        rightConsumer.accept(right);
        return this;
    }

    public <L1, R1> Either<L1, R1> map(Function<L, L1> leftFunction, Function<R, R1> rightFunction) {
        Objects.requireNonNull(leftFunction);
        Objects.requireNonNull(rightFunction);
        return isLeft()
                ? new Either<>(leftFunction.apply(left), null)
                : new Either<>(null, rightFunction.apply(right));
    }
}
