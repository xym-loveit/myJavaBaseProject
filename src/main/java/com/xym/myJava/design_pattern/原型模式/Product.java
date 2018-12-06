package com.xym.myJava.design_pattern.原型模式;

/**
 * 复制功能接口(抽象原型类)
 *
 * @author xym
 * @create 2018-12-05 11:29
 */
public interface Product extends Cloneable {

    /**
     * use方法是用于“使用”的方法，具体怎么“使用”，则被交给子类去实现。
     *
     * @param s
     */
    void use(String s);

    /**
     * creatClone方法是用于复制实例的方法
     *
     * @return
     */
    Product creatClone();
}
