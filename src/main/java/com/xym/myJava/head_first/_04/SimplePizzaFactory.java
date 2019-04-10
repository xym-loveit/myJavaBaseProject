package com.xym.myJava.head_first._04;

/**
 * 生产Pizza的简单工厂类
 *
 * @author xym
 * @create 2019-04-10 10:41
 */
public class SimplePizzaFactory {

    /**
     * 所有客户端都使用该工厂方法来实例化pizza
     *
     * @param type
     * @return
     */
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        }
        return pizza;
    }
}
