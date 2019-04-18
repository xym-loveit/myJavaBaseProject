package com.xym.myJava.head_first._013;

/**
 * 测试模拟器
 *
 * @author xym
 * @create 2019-04-18 15:50
 */
public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        //simulator.simulate();
        simulator.simulateWithDecorator();
    }

    void simulate() {
        Quackable mallardDuck = new MallardDuck();
        Quackable redheadDuck = new RedheadDuck();
        Quackable duckCall = new DuckCall();
        Quackable rubberDuck = new RubberDuck();
        //通过适配器将鹅适配成鸭子
        Quackable gooseAdapter = new GooseAdapter(new Goose());

        System.out.println("------------------------Duck Simulator----");
        simulate(mallardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseAdapter);
    }

    /**
     * 采用装饰器模式装饰后
     */
    void simulateWithDecorator() {
        Quackable mallardDuck = new QuackCounter(new MallardDuck());
        Quackable redheadDuck = new QuackCounter(new RedheadDuck());
        Quackable duckCall = new QuackCounter(new DuckCall());
        Quackable rubberDuck = new QuackCounter(new RubberDuck());
        //通过适配器将鹅适配成鸭子
        Quackable gooseAdapter = new GooseAdapter(new Goose());

        System.out.println("------------------------Duck Simulator：With Decorator----");
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
