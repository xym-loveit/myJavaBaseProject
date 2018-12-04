package com.xym.myJava.design_pattern.抽象工厂;

/**
 * AK具体工厂类
 *
 * @author xym
 * @create 2018-12-04 11:36
 */
public class AK_Factory implements Factory {
    @Override
    public Gun produceGun() {
        return new AK();
    }

    @Override
    public Bullet produceBullet() {
        return new AK_Bullet();
    }
}
