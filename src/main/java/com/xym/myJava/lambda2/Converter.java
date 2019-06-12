package com.xym.myJava.lambda2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 14:07
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
