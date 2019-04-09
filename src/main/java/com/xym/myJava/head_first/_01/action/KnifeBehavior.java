package com.xym.myJava.head_first._01.action;

/**
 * 用匕首刺杀
 *
 * @author xym
 * @create 2019-04-08 16:55
 */
public class KnifeBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("用匕首刺杀");
    }
}
