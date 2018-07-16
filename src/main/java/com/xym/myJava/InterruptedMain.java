package com.xym.myJava;

public class InterruptedMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("当前线程被中断，线程退出");
                        break;
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("休眠时，被中断");

                        /**
                         * Thread.sleep方法由于中断而抛出异常，此时，它会清除中断标记，如果不加处理，那么在下一次循环开始时，就无法捕获这个中断，故在异常处理中，再次设置中断标记
                         */

                        //设置中断标记
                        Thread.currentThread().interrupt();
                    }
                }

            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }


}
