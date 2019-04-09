package com.xym.myJava.head_first._01.action;

/**
 * 女王
 *
 * @author xym
 * @create 2019-04-08 17:11
 */
public class Queen extends Character {

    public Queen() {
        this.weaponBehavior = new SwordBehavior();
    }

    @Override
    public void fight() {
        this.weaponBehavior.useWeapon();
    }
}
