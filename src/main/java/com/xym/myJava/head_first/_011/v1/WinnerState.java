package com.xym.myJava.head_first._011.v1;

/**
 * 赢家状态
 *
 * @author xym
 * @create 2019-04-16 17:35
 */
public class WinnerState implements State {
    private GumballMachineV2 gumballMachineV2;

    public WinnerState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }

    @Override
    public void insertQuarter() {
        System.out.println("不可能投钱");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("不可能退钱");
    }

    @Override
    public void turnCrank() {
        System.out.println("不可能摇糖果");
    }

    @Override
    public void dispense() {
        System.out.println("你是赢家，将得到2颗糖果");
        gumballMachineV2.releaseBall();
        if (gumballMachineV2.getCount() == 0) {
            gumballMachineV2.setState(gumballMachineV2.getSoldOutState());
        } else {
            gumballMachineV2.releaseBall();
            if (gumballMachineV2.getCount() > 0) {
                gumballMachineV2.setState(gumballMachineV2.getNoQuarterState());
            } else {
                System.out.println("糖果卖完了");
                gumballMachineV2.setState(gumballMachineV2.getSoldOutState());
            }
        }
    }
}
