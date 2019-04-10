package com.xym.myJava.head_first._04;

/**
 * 芝加哥风味的pizza店
 *
 * @author xym
 * @create 2019-04-10 11:39
 */
public class ChicagoPizzaStore extends AbstractPizzaStore {


    /**
     * 子类实现的工厂方法
     * <p>
     * 工厂方法将生产产品的细节封装（隐藏）,对外是抽象产品 Pizza
     *
     * @param type
     * @return
     */
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new ChicagoCheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new ChicagoVeggiePizza();
        } else if (type.equals("clam")) {
            pizza = new ChicagoClamPizza();
        } else if (type.equals("pepperoni")) {
            pizza = new ChicagoPepperoniPizza(null);
        }
        return pizza;
    }
}
