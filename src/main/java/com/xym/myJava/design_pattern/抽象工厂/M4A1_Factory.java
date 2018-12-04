package com.xym.myJava.design_pattern.抽象工厂;

/**
 * M4A1具体工厂类
 *
 * @author xym
 * @create 2018-12-04 11:36
 */
public class M4A1_Factory implements Factory {

    @Override
    public Gun produceGun() {
        return new M4A1();
    }

    @Override
    public Bullet produceBullet() {
        return new M4A1_Bullet();
    }
}
