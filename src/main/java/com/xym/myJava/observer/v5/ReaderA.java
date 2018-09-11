package com.xym.myJava.observer.v5;

/**
 * 读者A（具体观察者）
 *
 * @author xym
 * @create 2018-09-11 15:09
 */
public class ReaderA implements Observer {

    public ReaderA() {

    }

    @Override
    public void update(Object object) {
        System.out.println("我是读者A，收到了新书：" + object.toString());
    }
}
