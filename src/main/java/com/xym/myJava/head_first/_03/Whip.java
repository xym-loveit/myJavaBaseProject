package com.xym.myJava.head_first._03;

/**
 * 奶泡---具体装饰者
 *
 * @author xym
 * @create 2019-04-09 16:35
 */
public class Whip extends CondimentDecorator {

    /**
     * 其中包含被装饰者
     */
    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + ",Whip";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 0.3;
    }
}
