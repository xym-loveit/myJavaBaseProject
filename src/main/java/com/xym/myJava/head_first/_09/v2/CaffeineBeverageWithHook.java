package com.xym.myJava.head_first._09.v2;

/**
 * 茶和咖啡的基类--咖啡因
 * <p>
 * <p>
 * 模板方法核心类
 *
 * @author xym
 * @create 2019-04-12 14:13
 */
public abstract class CaffeineBeverageWithHook {

    /**
     * 茶和咖啡的二种冲泡方法
     * 1、把水煮沸
     * 2、用热水泡茶或咖啡-----（该步骤茶和咖啡不一样）
     * 3、把饮料倒进被子
     * 4、在饮料中加入适当的调料-----（该步骤茶和咖啡不一样）
     * <p>
     * 此方法为模板方法：定义为final，不允许修改
     */
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        //加上一个条件语句，如果该条件成立才会执行addCondiments，该方法成为钩子方法
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    /**
     * 交由不同的子类实现
     */
    abstract void brew();

    /**
     * 交由不同的子类实现
     */
    abstract void addCondiments();

    /**
     * 把水煮沸
     */
    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    boolean customerWantsCondiments() {
        return Boolean.TRUE;
    }
}
