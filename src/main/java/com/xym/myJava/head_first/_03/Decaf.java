package com.xym.myJava.head_first._03;

/**
 * 低咖啡因
 *
 * @author xym
 * @create 2019-04-09 16:43
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.68;
    }
}
