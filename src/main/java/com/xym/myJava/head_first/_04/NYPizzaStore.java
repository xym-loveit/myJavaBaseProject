package com.xym.myJava.head_first._04;

/**
 * 纽约风味pizza店
 *
 * @author xym
 * @create 2019-04-10 11:17
 */
public class NYPizzaStore extends AbstractPizzaStore {
    @Override
    protected Pizza createPizza(String type) {

        //纽约的原料工厂提供生产pizza的原料
        PizzaIngredientFactory factory = new NYPizzaIngredientFactoryImpl();

        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new NYCheesePizza(factory);
        } else if (type.equals("veggie")) {
            pizza = new NYVeggiePizza();
        } else if (type.equals("clam")) {
            pizza = new NYClamPizza();
        } else if (type.equals("pepperoni")) {
            pizza = new NYPepperoniPizza();
        }
        return pizza;
    }
}
