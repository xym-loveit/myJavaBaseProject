package com.xym.myJava.head_first._013.v1;

import com.xym.myJava.head_first._013.*;

/**
 * 测试模拟器
 *
 * @author xym
 * @create 2019-04-18 15:50
 */
public class DuckSimulatorV2 {
    public static void main(String[] args) {
        DuckSimulatorV2 simulator = new DuckSimulatorV2();
        AbstractDuckFactory countingDuckFactory = new CountingDuckFactory();
        simulator.simulateWithDecorator(countingDuckFactory);
    }

    /**
     * 采用工厂模式之后,参数变为抽象工厂
     */
    void simulateWithDecorator(AbstractDuckFactory factory) {
        Quackable mallardDuck = factory.createMallardDuck();
        Quackable redheadDuck = factory.createRedheadDuck();
        Quackable duckCall = factory.createDuckCall();
        Quackable rubberDuck = factory.createRubberDuck();
        //通过适配器将鹅适配成鸭子
        Quackable gooseAdapter = new GooseAdapter(new Goose());

        System.out.println("------------------------Duck Simulator：With AbstractFactory----");
        simulate(mallardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseAdapter);
        System.out.println("鸭子叫了 " + QuackCounter.getQuacks() + " 次！");
    }

    /**
     * 依赖接口不依赖具体实现类,且该方法为重载方法
     *
     * @param quackable
     */
    void simulate(Quackable quackable) {
        quackable.quack();
    }
}
