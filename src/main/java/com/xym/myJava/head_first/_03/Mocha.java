package com.xym.myJava.head_first._03;

/**
 * 摩卡装饰者----具体装饰者
 *
 * @author xym
 * @create 2019-04-09 16:26
 */
public class Mocha extends CondimentDecorator {

    /**
     * 被装饰者实例
     */
    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + ", Mocha";
    }

    /**
     * 被装置者的价格（饮料）+当前装置者的价格（调料）
     *
     * @return
     */
    @Override
    public double cost() {
        return this.beverage.cost() + 0.2;
    }
}
