package com.xym.myJava.head_first._13.v1;

import com.xym.myJava.head_first._13.*;
import com.xym.myJava.head_first._13.v3.*;

/**
 * 鸭子生产工厂类，每个方法创建一种产品，一种特定种类的Quackable，
 * 客户端并不知道实际的产品是什么，只知道实现了Quackable接口
 *
 * @author xym
 * @create 2019-04-18 16:19
 */
public class DuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }

    @Override
    public QuackableV3 createMallardDuckV3() {
        return new MallardDuckV3();
    }

    @Override
    public QuackableV3 createRedheadDuckV3() {
        return new RedheadDuckV3();
    }

    @Override
    public QuackableV3 createDuckCallV3() {
        return new DuckCallV3();
    }

    @Override
    public QuackableV3 createRubberDuckV3() {
        return new RubberDuckV3();
    }
}
