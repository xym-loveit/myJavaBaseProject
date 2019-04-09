package com.xym.myJava.head_first._02;

/**
 * 主体接口--用来注册观察者，并在数据变化时通知观察者
 *
 * @author xym
 * @create 2019-04-09 9:44
 */
public interface Subject {
    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObserver();
}
