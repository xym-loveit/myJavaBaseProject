package com.xym.myJava.design_pattern.建造者模式.KFC套餐;

/**
 * 具体的产品对象
 * <p>
 * KFC套餐
 *
 * @author xym
 * @create 2018-12-04 14:49
 */
public class Meal {
    /**
     * KFC套餐中主食
     */
    private String food;
    /**
     * KFC套餐中饮料
     */
    private String drink;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
