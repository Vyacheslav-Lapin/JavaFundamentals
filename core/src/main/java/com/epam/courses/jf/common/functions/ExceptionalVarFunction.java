package com.epam.courses.jf.common.functions;

@FunctionalInterface
public interface ExceptionalVarFunction<T, R, E extends Throwable> extends VarFunction<T, Exceptional<R, E>> {

    @SuppressWarnings("unchecked")
    R get(T... t) throws E;

    @SuppressWarnings("unchecked")
    @Override
    default Exceptional<R, E> apply(T... t) {
        try {
            return Exceptional.withValue(get(t));
        } catch (Throwable e) {
            return Exceptional.withException((E) e);
        }
    }
}
