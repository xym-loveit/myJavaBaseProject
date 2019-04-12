package com.xym.myJava.head_first._09;

/**
 * 咖啡因饮料的子类茶
 *
 * @author xym
 * @create 2019-04-12 14:23
 */
public class Tea extends CaffeineBeverage {

    /**
     * 泡茶
     */
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    /**
     * 茶加入柠檬
     */
    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
