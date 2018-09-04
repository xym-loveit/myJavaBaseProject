package com.xym.myJava.observer.v2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:19
 */
public class Baby02 implements Runnable {

    // 默认是睡着
    private boolean wakeup = false;

    private Mother02 mother;

    //1. 持有Mother的引用，修改构造函数
    public Baby02(Mother02 mother) {
        this.mother = mother;
    }

    public boolean isWakeup() {
        return wakeup;
    }

    public void setWakeup(boolean wakeup) {
        this.wakeup = wakeup;
    }

    //2. 在wakeUp方法中增加，通知母亲的逻辑
    public void wakeUp() {
        wakeup = true;
        this.mother.feed(this);
    }

    //3. 在run方法中，自己五秒后醒来，自己修改自己的状态
    @Override
    public void run() {
        while (!this.isWakeup()) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("宝宝还有" + (5 - i) + "秒醒来");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            this.wakeUp();
        }
    }
}
