package com.xym.myJava;

public interface MyFuture<V> {
    V get() throws Exception;
}
