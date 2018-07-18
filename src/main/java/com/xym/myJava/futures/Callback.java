package com.xym.myJava.futures;

/**
 * 任务完毕后的回调接口
 *
 * @author xym
 * @create 2018-07-18 15:07
 */
@FunctionalInterface
public interface Callback<T> {
    /**
     * 任务完成后会调用该方法，其中T为任务执行后的结果
     */
    void call(T t);

}
