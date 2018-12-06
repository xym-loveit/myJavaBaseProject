package com.xym.myJava.design_pattern.建造者模式.KFC套餐;

/**
 * 创建一个Product对象的各个部件指定的抽象接口
 *
 * @author xym
 * @create 2018-12-04 14:51
 */
public abstract class MealBuilder {
    Meal meal = new Meal();

    /**
     * KFC套餐中汉堡的主食
     */
    public abstract void buildFood();

    /**
     * KFC套餐中汉堡的饮料
     */
    public abstract void buildDrink();

    public Meal getMeal() {
        return meal;
    }
}
