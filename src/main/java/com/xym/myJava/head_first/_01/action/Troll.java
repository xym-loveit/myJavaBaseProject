package com.xym.myJava.head_first._01.action;

/**
 * Troll
 *
 * @author xym
 * @create 2019-04-08 17:14
 */
public class Troll extends Character {

    public Troll() {
        this.weaponBehavior = new AxeBehavior();
    }

    @Override
    public void fight() {
        this.weaponBehavior.useWeapon();
    }
}
