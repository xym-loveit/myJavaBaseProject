package com.xym.myJava.head_first._013.v2;

import com.xym.myJava.head_first._013.Goose;
import com.xym.myJava.head_first._013.GooseAdapter;
import com.xym.myJava.head_first._013.QuackCounter;
import com.xym.myJava.head_first._013.Quackable;
import com.xym.myJava.head_first._013.v1.AbstractDuckFactory;
import com.xym.myJava.head_first._013.v1.CountingDuckFactory;

/**
 * 测试模拟器,加入组合模式后，用来管理鸭子很方便
 * <p>
 * 主群、绿头鸭2个群分开管理
 *
 * @author xym
 * @create 2019-04-18 15:50
 */
public class DuckSimulatorV3 {
    public static void main(String[] args) {
        DuckSimulatorV3 simulator = new DuckSimulatorV3();
        AbstractDuckFactory countingDuckFactory = new CountingDuckFactory();
        simulator.simulateWithDecorator(countingDuckFactory);
    }

    /**
     * 采用工厂模式之后,参数变为抽象工厂
     */
    void simulateWithDecorator(AbstractDuckFactory factory) {

        Quackable redheadDuck = factory.createRedheadDuck();
        Quackable duckCall = factory.createDuckCall();
        Quackable rubberDuck = factory.createRubberDuck();
        //通过适配器将鹅适配成鸭子
        Quackable gooseAdapter = new GooseAdapter(new Goose());

        System.out.println("------------------------Duck Simulator：With Composite-Flocks----");
        //主群
        Flock flock = new Flock();
        flock.add(redheadDuck);
        flock.add(duckCall);
        flock.add(rubberDuck);
        flock.add(gooseAdapter);
        //绿头鸭家族
        Flock flockOfMallards = new Flock();
        Quackable mallardDuck1 = factory.createMallardDuck();
        Quackable mallardDuck2 = factory.createMallardDuck();
        Quackable mallardDuck3 = factory.createMallardDuck();
        Quackable mallardDuck4 = factory.createMallardDuck();
        flockOfMallards.add(mallardDuck1);
        flockOfMallards.add(mallardDuck2);
        flockOfMallards.add(mallardDuck3);
        flockOfMallards.add(mallardDuck4);
        //将绿头鸭加入主群
        flock.add(flockOfMallards);

        //测试一整群
        simulate(flock);
        System.out.println("------------------------------");
        //只测试绿头鸭群
        simulate(flockOfMallards);
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
