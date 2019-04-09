package com.xym.myJava.head_first._03;

/**
 * 深烘焙咖啡--具体饮料
 *
 * @author xym
 * @create 2019-04-09 16:40
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 1.78;
    }
}
