package com.xym.myJava.observer.v1;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:15
 */
public class Baby implements Runnable {

    // 默认是睡着
    private boolean wakeup = false;

    // 醒来的行为
    public void wakeUp() {
        this.wakeup = true;
    }

    public boolean isWakeup() {
        return wakeup;
    }


    public void setWakeup(boolean wakeup) {
        this.wakeup = wakeup;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wakeUp();
    }
}
