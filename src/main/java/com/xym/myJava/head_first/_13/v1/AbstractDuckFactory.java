package com.xym.myJava.head_first._13.v1;

import com.xym.myJava.head_first._13.Quackable;
import com.xym.myJava.head_first._13.v3.QuackableV3;

/**
 * 鸭子抽象工厂类
 *
 * @author xym
 * @create 2019-04-18 16:16
 */
public abstract class AbstractDuckFactory {

    public abstract Quackable createMallardDuck();

    public abstract Quackable createRedheadDuck();

    public abstract Quackable createDuckCall();

    public abstract Quackable createRubberDuck();

    /***************************************V3 版本**********************************************/
    
    public abstract QuackableV3 createMallardDuckV3();

    public abstract QuackableV3 createRedheadDuckV3();

    public abstract QuackableV3 createDuckCallV3();

    public abstract QuackableV3 createRubberDuckV3();

}
