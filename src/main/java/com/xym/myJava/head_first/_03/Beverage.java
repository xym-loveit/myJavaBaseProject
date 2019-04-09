package com.xym.myJava.head_first._03;

/**
 * 饮料抽象父类
 *
 * @author xym
 * @create 2019-04-09 16:14
 */
public abstract class Beverage {

    String description = "Unknow Beverage";

    /**
     * 饮料描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 价格
     *
     * @return
     */
    public abstract double cost();
}
