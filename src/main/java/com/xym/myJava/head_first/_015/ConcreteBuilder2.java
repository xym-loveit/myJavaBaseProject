package com.xym.myJava.head_first._015;

/**
 * 具体建造者
 *
 * @author xym
 * @create 2019-04-23 15:45
 */
public class ConcreteBuilder2 extends Builder {
    @Override
    public void buildPartA() {
        product.setPartA("222-AAA");
        System.out.println("ConcreteBuilder2 创建A部分-----");
    }

    @Override
    public void buildPartB() {
        product.setPartB("222-BBB");
        System.out.println("ConcreteBuilder2 创建B部分-------");
    }

    @Override
    public void buildPartC() {
        product.setPartC("222-CCC");
        System.out.println("ConcreteBuilder2 创建C部分-----");
    }
}
