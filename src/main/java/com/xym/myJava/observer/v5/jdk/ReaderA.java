package com.xym.myJava.observer.v5.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-11 15:23
 */
public class ReaderA implements Observer {

    public ReaderA() {

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("我是读者A，收到了新书：" + arg.toString());
    }
}
