package com.xym.myJava.design_pattern.抽象工厂;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-04 11:37
 */
public class Test {
    public static void main(String[] args) {
        Factory factory;
        Gun gun;
        Bullet bullet;

        factory = new AK_Factory();
        bullet = factory.produceBullet();
        bullet.load();
        gun = factory.produceGun();
        gun.shooting();

    }
}
