package com.xym.myJava.head_first._04;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-10 10:27
 */
public class NYCheesePizza extends Pizza {


    PizzaIngredientFactory factory;

    public NYCheesePizza(PizzaIngredientFactory factory) {
        this.factory = factory;
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + getName());
        this.dough = this.factory.createDough();
        this.cheese = this.factory.createCheese();
        this.clams = this.factory.createClams();
    }

    @Override
    public String getName() {
        return "纽约风格的奶酪pizza";
    }
}
