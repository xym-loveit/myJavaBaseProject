package com.xym.myJava.observer.v2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:20
 */
public class Mother02 {

    private Baby02 baby;

    public Mother02() {
    }

    public Mother02(Baby02 baby) {
        this.baby = baby;
    }

    public void feed(Baby02 baby) {
        System.out.println("已经给宝贝喂食");
    }
}
