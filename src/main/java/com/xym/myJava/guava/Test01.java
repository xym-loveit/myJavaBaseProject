package com.xym.myJava.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-08-07 11:48
 */
public class Test01 {
    public static void main(String[] args) {
        //新建一个每秒限制3个的令牌桶
        double v = 1 / 60d;
        RateLimiter rateLimiter = RateLimiter.create(v);
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        long stime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            //executor.execute(new Runnable() {
            //    @Override
            //public void run() {
            System.out.println(i);
            //获取令牌桶中一个令牌，最多等待10秒
            if (rateLimiter.tryAcquire(1, 5, TimeUnit.SECONDS)) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "--" + i);
              /*  try {
                    //Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }else{
                i--;
            }
            //}
            //});
        }
        long etime = System.currentTimeMillis();
        System.out.println(etime - stime + " ms");
        //executor.shutdown();
    }
}
