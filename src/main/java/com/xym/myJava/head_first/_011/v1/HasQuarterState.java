package com.xym.myJava.head_first._011.v1;

import java.util.Random;

/**
 * 给钱的状态
 *
 * @author xym
 * @create 2019-04-16 17:05
 */
public class HasQuarterState implements State {
    /**
     * 随机数用来产生10%的赢家机会
     */
    private Random random = new Random(System.currentTimeMillis());
    private GumballMachineV2 gumballMachineV2;

    public HasQuarterState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已经投钱了，不能再投入!");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("钱已退还");
        this.gumballMachineV2.setState(this.gumballMachineV2.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("拿到糖果卷");
        //转动曲轴的时候，制造一定的机会成为赢家(幸运者)
        int winner = random.nextInt(10);
        if (winner == 0 && this.gumballMachineV2.getCount() > 1) {
            this.gumballMachineV2.setState(this.gumballMachineV2.getWinnerState());
        } else {
            this.gumballMachineV2.setState(this.gumballMachineV2.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("不可能发放");
    }
}
