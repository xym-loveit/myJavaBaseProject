package com.xym.myJava.head_first._01.action;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 17:03
 */
public class BowAndArrowBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("用弓箭射杀");
    }
}
