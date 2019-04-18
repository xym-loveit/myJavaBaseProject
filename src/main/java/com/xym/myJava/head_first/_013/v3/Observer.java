package com.xym.myJava.head_first._013.v3;

/**
 * 观察者---被观察者发生变化时被通知人
 *
 * @author xym
 * @create 2019-04-18 17:29
 */
public interface Observer {
    /**
     * @param quackObservable 被观察者对象(事件源)
     */
    void update(QuackObservable quackObservable);
}
