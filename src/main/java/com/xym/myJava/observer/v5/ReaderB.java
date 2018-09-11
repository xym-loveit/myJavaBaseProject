package com.xym.myJava.observer.v5;

/**
 * 读者B（具体观察者）
 *
 * @author xym
 * @create 2018-09-11 15:09
 */
public class ReaderB implements Observer {

    public ReaderB() {

    }

    @Override
    public void update(Object object) {
        System.out.println("我是读者B，收到了新书：" + object.toString());
    }
}
