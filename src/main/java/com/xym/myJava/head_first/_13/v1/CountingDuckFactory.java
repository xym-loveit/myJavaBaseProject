package com.xym.myJava.head_first._13.v1;

import com.xym.myJava.head_first._13.*;
import com.xym.myJava.head_first._13.v3.*;

/**
 * 具有计数器的鸭子生产工厂类
 * <p>
 * 每个方法都会先用叫声计数器装饰者将Quackable包装起来，
 * 客户端并不知道有何不同，只知道实现了Quackable接口，但是巡逻员可以放心使用，所有的叫声都会
 * 准确无误计数
 *
 * @author xym
 * @create 2019-04-18 16:22
 */
public class CountingDuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    @Override
    public Quackable createRedheadDuck() {
        return new QuackCounter(new RedheadDuck());
    }

    @Override
    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }

    @Override
    public QuackableV3 createMallardDuckV3() {
        return new QuackCounterV3(new MallardDuckV3());
    }

    @Override
    public QuackableV3 createRedheadDuckV3() {
        return new QuackCounterV3(new RedheadDuckV3());
    }

    @Override
    public QuackableV3 createDuckCallV3() {
        return new QuackCounterV3(new DuckCallV3());
    }

    @Override
    public QuackableV3 createRubberDuckV3() {
        return new QuackCounterV3(new RubberDuckV3());
    }
}
