package com.xym.myJava.futures;

/**
 * 任务执行结果接口
 *
 * @author xym
 * @create 2018-07-18 13:57
 */
public interface Future<T> {

    /**
     * 返回计算后的结果，方法会陷入阻塞状态
     *
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;

    /**
     * 判断任务是否已经被执行完成
     *
     * @return
     */
    boolean done();
}
