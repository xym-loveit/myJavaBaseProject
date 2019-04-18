package com.xym.myJava.head_first._013.v3;

import com.xym.myJava.head_first._013.Goose;
import com.xym.myJava.head_first._013.v1.AbstractDuckFactory;
import com.xym.myJava.head_first._013.v1.CountingDuckFactory;

public class DuckSimulatorV3 {
    public static void main(String[] args) {
        DuckSimulatorV3 simulator = new DuckSimulatorV3();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();

        simulator.simulate(duckFactory);
    }

    void simulate(AbstractDuckFactory duckFactory) {

        QuackableV3 redheadDuck = duckFactory.createRedheadDuckV3();
        QuackableV3 duckCall = duckFactory.createDuckCallV3();
        QuackableV3 rubberDuck = duckFactory.createRubberDuckV3();
        QuackableV3 gooseDuck = new GooseAdapterV3(new Goose());

        FlockV3 flockOfDucks = new FlockV3();

        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        FlockV3 flockOfMallards = new FlockV3();

        QuackableV3 mallardOne = duckFactory.createMallardDuckV3();
        QuackableV3 mallardTwo = duckFactory.createMallardDuckV3();
        QuackableV3 mallardThree = duckFactory.createMallardDuckV3();
        QuackableV3 mallardFour = duckFactory.createMallardDuckV3();

        flockOfMallards.add(mallardOne);
        flockOfMallards.add(mallardTwo);
        flockOfMallards.add(mallardThree);
        flockOfMallards.add(mallardFour);

        flockOfDucks.add(flockOfMallards);

        System.out.println("\nDuck Simulator: With Observer");

        Quackologist quackologist = new Quackologist();
        //注册观察者
        flockOfDucks.registerObserver(quackologist);

        simulate(flockOfDucks);

        System.out.println("\nThe ducks quacked " +
                QuackCounterV3.getQuacks() +
                " times");
    }

    void simulate(QuackableV3 duck) {
        duck.quack();
    }
}