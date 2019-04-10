package com.xym.myJava.head_first._04;

/**
 * pizza测试类
 *
 * @author xym
 * @create 2019-04-10 11:32
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        //2家pizza店
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        Pizza cheese = nyPizzaStore.orderPizza("cheese");
        System.out.println("张三的pizza：" + cheese.getName());
        Pizza cheese2 = chicagoPizzaStore.orderPizza("cheese");
        System.out.println("李四的pizza：" + cheese2.getName());
    }
}
