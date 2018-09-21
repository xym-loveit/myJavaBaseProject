package com.xym.myJava.guava;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-19 16:51
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                if (new Random().nextInt(3) == 2) {
                    throw new NullPointerException();
                }
                return 1;
            }
        });

        FutureCallback futureCallback = new FutureCallback() {
            @Override
            public void onSuccess(@Nullable Object result) {
                System.out.println("===============" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("======" + t.getMessage());
            }
        };

        Futures.addCallback(listenableFuture, futureCallback);

    }
}
