package com.xym.myJava.head_first._01.action;

/**
 * 动作冒险游戏-角色抽象类
 *
 * @author xym
 * @create 2019-04-08 16:51
 */
public abstract class Character {
    protected WeaponBehavior weaponBehavior;

    public void setWeapon(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    /**
     * 战斗
     */
    public abstract void fight();
}
