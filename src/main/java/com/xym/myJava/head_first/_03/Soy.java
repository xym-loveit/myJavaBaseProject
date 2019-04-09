package com.xym.myJava.head_first._03;

/**
 * 豆浆-具体装饰者---继承抽象装饰器
 *
 * @author xym
 * @create 2019-04-09 16:33
 */
public class Soy extends CondimentDecorator {

    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 0.3;
    }
}
