package com.xym.myJava.observer.v5;

/**
 * 观察者接口
 *
 * @author xym
 * @create 2018-09-11 15:01
 */
public interface Observer {
    /**
     * 观察者通知方法
     *
     * @param object
     */
    void update(Object object);
}
