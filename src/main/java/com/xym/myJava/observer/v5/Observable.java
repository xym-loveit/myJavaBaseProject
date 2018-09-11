package com.xym.myJava.observer.v5;

import java.util.Vector;

/**
 * 被观察者（通知人）
 *
 * @author xym
 * @create 2018-09-11 15:02
 */
public abstract class Observable {
    /**
     * 观察者数组
     */
    private Vector<Observer> observers = new Vector<>();

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void delObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Book book) {
        for (Observer observer : this.observers) {
            observer.update(book);
        }
    }
}
