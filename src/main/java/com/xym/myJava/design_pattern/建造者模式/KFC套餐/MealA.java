package com.xym.myJava.design_pattern.建造者模式.KFC套餐;

/**
 * A套餐
 *
 * @author xym
 * @create 2018-12-04 15:18
 */
public class MealA extends MealBuilder {
    @Override
    public void buildFood() {
        meal.setFood("薯条");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("可乐");
    }
}
