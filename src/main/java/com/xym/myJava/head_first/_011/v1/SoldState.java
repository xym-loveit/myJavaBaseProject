package com.xym.myJava.head_first._011.v1;

/**
 * 出售的状态
 *
 * @author xym
 * @create 2019-04-16 17:10
 */
public class SoldState implements State {

    private GumballMachineV2 gumballMachineV2;

    public SoldState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }

    @Override
    public void insertQuarter() {
        System.out.println("请等待，我们刚刚已经给过你糖果");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你已经拿到了糖果，还想退钱？");
    }

    @Override
    public void turnCrank() {
        System.out.println("你已经拿过糖果了，还想再拿？");
    }

    @Override
    public void dispense() {
        //找机器发放糖果，在根据存量改变状态
        gumballMachineV2.releaseBall();
        if (gumballMachineV2.getCount() > 0) {
            gumballMachineV2.setState(gumballMachineV2.getNoQuarterState());
        } else {
            System.out.println("发糖果啦...");
            gumballMachineV2.setState(gumballMachineV2.getSoldOutState());
        }
    }
}
