package com.xym.myJava.observer.v3;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:26
 */
public class Baby03 implements Runnable {

    private Mother03 mother;
    private Father father;

    public Baby03(Mother03 mother, Father father) {
        this.mother = mother;
        this.father = father;
    }

    public void wakeUp() {
        this.mother.feed(new WakeUpEvent(true, this));
        this.father.play(new WakeUpEvent(true, this));
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
