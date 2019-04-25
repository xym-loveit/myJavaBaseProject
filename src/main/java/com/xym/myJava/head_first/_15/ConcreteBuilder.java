package com.xym.myJava.head_first._15;

/**
 * 具体的建造者，可以自由发挥创建不同的产品
 *
 * @author xym
 * @create 2019-04-23 15:43
 */
public class ConcreteBuilder extends Builder {
    @Override
    public void buildPartA() {
        product.setPartA("111-AAA");
        System.out.println("ConcreteBuilder 创建A部分");
    }

    @Override
    public void buildPartB() {
        product.setPartB("111-BBB");
        System.out.println("ConcreteBuilder 创建B部分");
    }

    @Override
    public void buildPartC() {
        product.setPartC("111-CCC");
        System.out.println("ConcreteBuilder 创建C部分");
    }
}
