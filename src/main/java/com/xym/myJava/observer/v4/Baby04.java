package com.xym.myJava.observer.v4;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:39
 */
public class Baby04 implements Runnable {

    private List<WakeUpListener> wakeUpListeners = new ArrayList();

    public void addListeners(WakeUpListener wakeUpListener) {
        this.wakeUpListeners.add(wakeUpListener);
    }

    public void wakeUp() {
        for (WakeUpListener listener : wakeUpListeners) {
            listener.actiontoWakenUp(new WakeUpEvent02(true, this));
        }
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
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
            flag = false;
        }
    }
}
