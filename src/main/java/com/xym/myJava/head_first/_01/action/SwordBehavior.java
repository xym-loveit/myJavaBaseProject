package com.xym.myJava.head_first._01.action;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 17:06
 */
public class SwordBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("用宝剑挥舞");
    }
}
