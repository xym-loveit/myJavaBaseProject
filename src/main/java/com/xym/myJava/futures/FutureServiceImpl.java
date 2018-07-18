package com.xym.myJava.futures;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FutureService的实现类
 *
 * @author xym
 * @create 2018-07-18 14:37
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    /**
     * 为执行的线程指定名字前缀
     */
    private static final String FUTURE_THREAD_PREFIX = "FUTURE-";
    /**
     * 线程编号
     */
    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(getNextName()) {
            @Override
            public void run() {
                runnable.run();
                //任务结束后将null作为结果传递为future
                futureTask.finish(null);
            }
        }.start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        FutureTask<OUT> objectFutureTask = new FutureTask<>();
        new Thread(getNextName()) {
            @Override
            public void run() {
                OUT result = task.get(input);
                //任务执行结束后，将真实的结果通过finish方法传递给future
                objectFutureTask.finish(result);
            }
        }.start();
        return objectFutureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        FutureTask<OUT> objectFutureTask = new FutureTask<>();
        new Thread(getNextName()) {
            @Override
            public void run() {
                OUT result = task.get(input);
                //任务执行结束后，将真实的结果通过finish方法传递给future
                objectFutureTask.finish(result);
                if (callback != null) {
                    callback.call(result);
                }
            }
        }.start();
        return objectFutureTask;
    }
}
