package com.xym.myJava.observer.v1;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:16
 */
public class Mother implements Runnable {

    private Baby baby;

    public Mother(Baby baby) {
        this.baby = baby;
    }

    public void feed(Baby baby) {
        System.out.println("已经给宝贝喂食");
    }

    @Override
    public void run() {
        while (!baby.isWakeup()) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("宝宝还有" + (5 - i) + "秒醒来");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        this.feed(baby);
    }
}
