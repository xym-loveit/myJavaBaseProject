package com.xym.myJava.head_first._011.v1;

/**
 * 没给钱状态
 *
 * @author xym
 * @create 2019-04-16 16:45
 */
public class NoQuarterState implements State {
    /**
     * 需要得到糖果机的引用并存起来
     */
    private GumballMachineV2 gumballMachineV2;

    public NoQuarterState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }

    @Override
    public void insertQuarter() {
        System.out.println("收到你的钱");
        this.gumballMachineV2.setState(gumballMachineV2.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你没给钱，我怎么退？");
    }

    @Override
    public void turnCrank() {
        System.out.println("请先投钱，才能摇糖果");
    }

    @Override
    public void dispense() {
        System.out.println("请先投钱，才能发放糖果");
    }
}
