package com.xym.myJava.io.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author xym
 * @create 2018-12-29 15:05
 */
public class TimeServerV2HandlerExecutePool {
    private ExecutorService executorService;

    public TimeServerV2HandlerExecutePool(int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(java.lang.Runnable task) {
        executorService.execute(task);
    }
}
