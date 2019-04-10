package com.xym.myJava.head_first._04;

/**
 * 抽象pizza店---将依赖pizza简单工厂转为框架形成的模板方法
 *
 * @author xym
 * @create 2019-04-10 11:13
 */
public abstract class AbstractPizzaStore {

    /**
     * 定义框架，将自由扩展的逻辑放在模板方法中(createPizza())
     *
     * @param type
     * @return
     */
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = this.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 使用模板方法
     * <p>
     * 工厂方法用来处理对象的创建，并将这样的行为封装在子类中，这样，
     * 客户端程序中关于超类的代码和子类对象的创建代码解耦了
     *
     * @param type
     * @return
     */
    protected abstract Pizza createPizza(String type);
}
