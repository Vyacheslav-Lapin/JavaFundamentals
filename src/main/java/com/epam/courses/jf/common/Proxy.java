package com.epam.courses.jf.common;

@FunctionalInterface
public interface Proxy<T> {
    @Private
    T toSrc();
}
