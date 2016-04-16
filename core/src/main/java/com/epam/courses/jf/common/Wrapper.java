package com.epam.courses.jf.common;

@FunctionalInterface
public interface Wrapper<T> {
    @Private
    T toSrc();
}
