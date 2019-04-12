package com.xym.myJava.head_first._07;

/**
 * 适配器，将火鸡（被适配者）适配成鸭子（目标）
 * <p>
 * 当只有火鸡，缺少鸭子时使用
 *
 * @author xym
 * @create 2019-04-12 10:08
 */
public class TurkeyAdapter implements Duck {

    /**
     * 当前被适配对象
     */
    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        this.turkey.gobble();
    }

    /**
     * 火鸡飞行距离较短，5次的飞行相当一次鸭子的飞行
     */
    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            this.turkey.fly();
        }
    }
}
