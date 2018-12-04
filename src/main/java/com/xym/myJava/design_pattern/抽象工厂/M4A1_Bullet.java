package com.xym.myJava.design_pattern.抽象工厂;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-04 11:35
 */
public class M4A1_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("Load bullets with M4A1");
    }
}
