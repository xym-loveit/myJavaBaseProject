package com.xym.myJava.head_first._04;

/**
 * pizza原料工厂接口---负责生产做pizza的所有原料
 * <p>
 * 抽象工厂模式中采用--聚合工厂方法的做法，来创建出一组产品，称为产品族,并且每类产品族只依赖接口
 * <p>
 * 例如以下都是产品接口（Dough、Sauce、Cheese、Veggies、Pepperoni、Clams）
 *
 * @author xym
 * @create 2019-04-10 14:34
 */
public interface PizzaIngredientFactory {


    /**
     * 抽象工厂的工厂方法
     *
     * @return
     */
    Dough createDough();

    /**
     * 抽象工厂的工厂方法
     *
     * @return
     */
    Sauce createSauce();

    /**
     * 抽象工厂的工厂方法
     *
     * @return
     */
    Cheese createCheese();

    /**
     * 抽象工厂的工厂方法
     *
     * @return
     */
    Veggies[] createVeggies();

    /**
     * 抽象工厂的工厂方法
     *
     * @return
     */
    Pepperoni createPepperoin();

    /**
     * 抽象工厂的工厂方法
     *
     * @return
     */
    Clams createClams();
}
