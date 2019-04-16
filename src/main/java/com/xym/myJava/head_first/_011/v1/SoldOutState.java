package com.xym.myJava.head_first._011.v1;

/**
 * 售完状态
 *
 * @author xym
 * @create 2019-04-16 17:51
 */
public class SoldOutState implements State {

    private GumballMachineV2 gumballMachineV2;

    public SoldOutState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }

    @Override
    public void insertQuarter() {
        System.out.println("当前糖果已卖完，不能投钱");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("糖果售完，你不可能投钱，更不可能退钱！");
    }

    @Override
    public void turnCrank() {
        System.out.println("糖果机暂无糖果出售,别摇啦");
    }

    @Override
    public void dispense() {
        System.out.println("不可能发放");
    }
}
