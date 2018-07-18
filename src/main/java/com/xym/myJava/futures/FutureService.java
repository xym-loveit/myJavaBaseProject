package com.xym.myJava.futures;


/**
 * 提交任务接口
 *
 * @author xym
 * @create 2018-07-18 14:00
 */
public interface FutureService<IN, OUT> {

    /**
     * 提交不需要返回值的任务，Future.get方法返回的将会是null
     *
     * @param runnable
     * @return
     */
    Future<?> submit(Runnable runnable);

    /**
     * 提交需要返回值的任务，其中task接口代替了Runnable接口
     *
     * @param task
     * @param input
     * @return
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input);


    /**
     * 提交需要返回值的任务，其中task接口代替了Runnable接口，callback指定任务完毕之后的回调
     *
     * @param task
     * @param input
     * @return
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);

    /**
     * 使用静态方法创建一个FutureService的实现
     */
    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
