package com.xym.myJava.design_pattern.抽象工厂;

/**
 * 创建工厂接口
 *
 * @author xym
 * @create 2018-12-04 11:35
 */
public interface Factory {

    public Gun produceGun();

    public Bullet produceBullet();
}
