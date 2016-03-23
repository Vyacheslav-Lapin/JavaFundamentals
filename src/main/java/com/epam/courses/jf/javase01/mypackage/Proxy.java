package com.epam.courses.jf.javase01.mypackage;

@FunctionalInterface
public interface Proxy<T> {
    @Private
    T toSrc();
}
