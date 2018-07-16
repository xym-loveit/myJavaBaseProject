package com.xym.myJava;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
