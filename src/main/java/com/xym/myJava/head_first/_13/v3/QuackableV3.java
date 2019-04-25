package com.xym.myJava.head_first._13.v3;

/**
 * 呱呱叫接口继续了可被观察的接口---实现观察者模式
 *
 * @author xym
 * @create 2019-04-18 15:44
 */
public interface QuackableV3 extends QuackObservable {
    void quack();
}
