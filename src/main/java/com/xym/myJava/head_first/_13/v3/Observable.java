package com.xym.myJava.head_first._13.v3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 被观察注册观察者和通知观察者的逻辑封装类/辅助类
 * <p>
 * 可以复用一份代码，封装后的 逻辑可以统一管理
 *
 * @author xym
 * @create 2019-04-18 17:35
 */
public class Observable implements QuackObservable {
    //所有的观察者
    private ArrayList<Observer> observers = new ArrayList<>();
    //是真正的被观察者对象
    private QuackObservable observable;

    public Observable(QuackObservable observable) {
        this.observable = observable;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        Iterator iterator = observers.iterator();
        while (iterator.hasNext()) {
            Observer next = (Observer) iterator.next();
            next.update(observable);
        }
    }
}
