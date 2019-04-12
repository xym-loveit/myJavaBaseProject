package com.xym.myJava.head_first._09;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-12 14:25
 */
public class Coffee extends CaffeineBeverage {

    /**
     * 做咖啡
     */
    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    /**
     * 加入糖和牛奶
     */
    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
