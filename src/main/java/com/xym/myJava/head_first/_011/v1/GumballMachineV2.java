package com.xym.myJava.head_first._011.v1;


/**
 * 糖果机程序-------------状态模式v2
 * <p>
 * 采用状态模式改良后的v2版本
 *
 * @author xym
 * @create 2019-04-16 15:53
 */
public class GumballMachineV2 {
    /**
     * 售完
     */
    //final static int SOLD_OUT = 0;
    State soldOutState;
    /**
     * 没给钱
     */
    //final static int NO_QUARTER = 1;
    State noQuarterState;
    /**
     * 有钱
     */
    //final static int HAS_QUARTER = 2;
    State hasQuarterState;
    /**
     * 出售
     */
    //final static int SOLD = 3;
    State soldState;
    /**
     * 赢家状态
     */
    State winnerState;
    /**
     * 实例变量，跟踪当前状态，开始设置为售完
     */
    //int state = SOLD_OUT;
    State state = soldOutState;
    /**
     * 第二个实例变量，用来追踪机器内糖果的数目
     */
    int count = 0;

    /**
     * 构造器需要初始化糖果库存量当做参数
     * 如果库存量不为0的话，机器就会进入“没钱”的状态，也就是说
     * 等着别人投入钱，如果糖果数目为0的话，机器就会保持在“售完”的状态
     *
     * @param count
     */
    public GumballMachineV2(int count) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);
        this.count = count;
        if (count > 0) {
            //如果糖果超过0，状态更改为未投钱
            state = noQuarterState;
        }
    }

    /**
     * 投钱动作
     */
    public void insertQuarter() {
        state.insertQuarter();
    }

    /**
     * 退钱
     */
    public void ejectQuarter() {
        state.ejectQuarter();
    }

    /**
     * 转动曲柄，要糖果
     */
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    /**
     * 该方法允许其他对象(自定义的状态对象)将机器的状态转换到不同的状态
     *
     * @param state
     */
    void setState(State state) {
        this.state = state;
    }

    /**
     * 释放糖果
     */
    void releaseBall() {
        System.out.println("发放一颗糖果");
        if (count != 0) {
            count = count - 1;
        }
    }

    /**
     * 自动回填糖果(售光之后有用的方法)
     *
     * @param count
     */
    void refill(int count) {
        this.count = count;
        setState(noQuarterState);
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getState() {
        return state;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "GumballMachineV2{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}
