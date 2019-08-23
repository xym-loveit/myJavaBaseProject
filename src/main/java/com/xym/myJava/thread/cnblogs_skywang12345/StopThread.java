package com.xym.myJava.thread.cnblogs_skywang12345;

import java.math.BigDecimal;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-23 23:18
 */
public class StopThread extends Thread {

    private volatile BigDecimal flag = BigDecimal.ONE;

    public StopThread(String name) {
        super(name);
    }

    protected void stopTask() {
        flag = BigDecimal.valueOf(10000099999L);
    }

    @Override
    public void run() {
        int i = 0;
        while (flag.subtract(BigDecimal.ONE).longValue() == 0) {
            System.out.println(Thread.currentThread().getName() + "----" + (++i));
        }
        System.out.println("over----" + flag);
    }

    public static void main(String[] args) throws InterruptedException {
        StopThread st = new StopThread("st");
        st.start();
        Thread.sleep(1000);
        st.stopTask();
    }
}
