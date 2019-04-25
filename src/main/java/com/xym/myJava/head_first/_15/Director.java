package com.xym.myJava.head_first._15;

/**
 * 指挥者类，针对接口编程，跟client打交道
 * 指挥具体的建造者建造不同的产品,对客户端隐藏了建造和组装方法
 *
 * @author xym
 * @create 2019-04-23 15:37
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构建与组装方法
     */
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
