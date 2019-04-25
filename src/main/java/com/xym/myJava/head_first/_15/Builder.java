package com.xym.myJava.head_first._15;

/**
 * 抽象产品类
 *
 * @author xym
 * @create 2019-04-23 15:34
 */
public abstract class Builder {
    //创建产品对象
    protected Product product = new Product();

    /**
     * 每个部分的创建过程交给子类去实现
     */
    public abstract void buildPartA();

    /**
     * 每个部分的创建过程交给子类去实现
     */
    public abstract void buildPartB();

    /**
     * 每个部分的创建过程交给子类去实现
     */
    public abstract void buildPartC();

    //返回产品对象
    public Product getResult() {
        return product;
    }
}
