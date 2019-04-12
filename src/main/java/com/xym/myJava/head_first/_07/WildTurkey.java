package com.xym.myJava.head_first._07;

/**
 * 火鸡的实现类
 *
 * @author xym
 * @create 2019-04-12 10:06
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    /**
     * 火鸡的飞行距离很短
     */
    @Override
    public void fly() {
        System.out.println("I'm fly s short distance");
    }
}
