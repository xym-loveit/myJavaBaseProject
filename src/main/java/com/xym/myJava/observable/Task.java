package com.xym.myJava.observable;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-18 9:54
 */
@FunctionalInterface
public interface Task<T> {

    /**
     * 任务执行接口，改接口允许有返回值
     *
     * @return
     */
    T call();
}
