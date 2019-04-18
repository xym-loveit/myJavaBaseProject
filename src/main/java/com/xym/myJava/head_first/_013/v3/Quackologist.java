package com.xym.myJava.head_first._013.v3;

/**
 * 观察者实现类-------被观察者发生变化后通知对象
 *
 * @author xym
 * @create 2019-04-18 17:55
 */
public class Quackologist implements Observer {
    @Override
    public void update(QuackObservable quackObservable) {
        System.out.println(quackObservable + " quacked!");
    }

    @Override
    public String toString() {
        return "Quackologist";
    }
}
