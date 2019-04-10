package com.xym.myJava.head_first._04;

/**
 * pizza店依赖pizza工厂类生产pizza，屏蔽了生产细节
 *
 * @author xym
 * @create 2019-04-10 10:54
 */
public class PizzaStore {

    /**
     * 依赖简单工厂
     */
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
