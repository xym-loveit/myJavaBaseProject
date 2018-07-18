package com.xym.myJava.futures;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-18 14:03
 */
@FunctionalInterface
public interface Task<IN, OUT> {

    /**
     * 给定一个参数经过计算返回结果
     *
     * @param input
     * @return
     */
    OUT get(IN input);
}
