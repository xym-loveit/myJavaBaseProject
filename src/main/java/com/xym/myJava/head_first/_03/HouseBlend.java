package com.xym.myJava.head_first._03;

/**
 * 混合咖啡
 *
 * @author xym
 * @create 2019-04-09 16:23
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 1.89;
    }
}
