package com.xym.myJava.head_first._11;


/**
 * 糖果机程序-------------状态模式v1
 *
 * @author xym
 * @create 2019-04-16 15:53
 */
public class GumballMachine {
    /**
     * 售完
     */
    final static int SOLD_OUT = 0;
    /**
     * 没给钱
     */
    final static int NO_QUARTER = 1;
    /**
     * 有钱
     */
    final static int HAS_QUARTER = 2;
    /**
     * 出售
     */
    final static int SOLD = 3;
    /**
     * 实例变量，跟踪当前状态，开始设置为售完
     */
    int state = SOLD_OUT;
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
    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    /**
     * 投钱动作
     */
    public void insertQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("已经投钱了，不能再投入!");
        } else if (NO_QUARTER == state) {
            //改变状态
            state = HAS_QUARTER;
            System.out.println("收到你的钱");
        } else if (state == SOLD) {
            System.out.println("请等待，我们刚刚已经给过你糖果");
        } else if (state == SOLD_OUT) {
            System.out.println("当前糖果已卖完，不能投钱");
        }
    }

    /**
     * 退钱
     */
    public void ejectQuarter() {
        if (state == HAS_QUARTER) {
            state = NO_QUARTER;
            System.out.println("钱已退还");
        } else if (state == NO_QUARTER) {
            System.out.println("你没给钱，我怎么退？");
        } else if (state == SOLD_OUT) {
            System.out.println("糖果售完，你不可能投钱，更不可能退钱！");
        } else if (state == SOLD) {
            System.out.println("你已经拿到了糖果，还想退钱？");
        }
    }

    /**
     * 转动曲柄，要糖果
     */
    public void turnCrank() {
        if (state == SOLD) {
            System.out.println("你已经拿过糖果了，还想再拿？");
        } else if (state == NO_QUARTER) {
            System.out.println("请先投钱，才能摇糖果");
        } else if (state == SOLD_OUT) {
            System.out.println("糖果机暂无糖果出售,别摇啦");
        } else if (state == HAS_QUARTER) {
            System.out.println("拿到糖果卷");
            //改变状态为出售
            state = SOLD;
            dispense();
        }
    }

    /**
     * 发放糖果
     */
    public void dispense() {
        if (state == SOLD) {
            System.out.println("发糖果啦...");
            count = count - 1;
            if (count == 0) {
                System.out.println("糖果卖完了");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        } else if (state == SOLD_OUT) {
            System.out.println("不可能发放");
        } else if (state == NO_QUARTER) {
            System.out.println("请先投钱，才能发放糖果");
        } else if (state == HAS_QUARTER) {
            System.out.println("不可能发放");
        }
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}
