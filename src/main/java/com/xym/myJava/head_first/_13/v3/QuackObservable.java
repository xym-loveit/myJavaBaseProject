package com.xym.myJava.head_first._13.v3;

/**
 * 鸭子被观察者接口--------观察者模式
 * <p>
 * 可被人观察的对象
 *
 * @author xym
 * @create 2019-04-18 17:27
 */
public interface QuackObservable {

    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);

    void notifyObservers();
}
