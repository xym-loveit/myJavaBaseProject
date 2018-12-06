package com.xym.myJava.design_pattern.建造者模式.KFC套餐;

/**
 * B套餐
 *
 * @author xym
 * @create 2018-12-04 15:49
 */
public class MealB extends MealBuilder {
    @Override
    public void buildFood() {
        meal.setFood("鸡翅");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("柠檬果汁");
    }
}
