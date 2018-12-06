package com.xym.myJava.design_pattern.建造者模式.KFC套餐;

/**
 * 指挥者类（在此为KFC窗口服务员）
 * <p>
 * 构建一个使用Builder接口的对象。
 * 它主要是用于创建一个复杂的对象，它主要有两个作用，
 * <p>
 * 一是：隔离了客户与对象的生产过程，
 * 二是：负责控制产品对象的生产过程。
 *
 * @author xym
 * @create 2018-12-04 15:54
 */
public class KFCWaiter {
    private MealBuilder mealBuilder;

    public KFCWaiter(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal construct() {
        //准备食物
        mealBuilder.buildFood();
        //准备饮料
        mealBuilder.buildDrink();

        //准备完毕，返回一个完整的套餐给客户
        return mealBuilder.getMeal();
    }
}
