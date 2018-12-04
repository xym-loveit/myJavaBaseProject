package com.xym.myJava.design_pattern.抽象工厂;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-04 11:34
 */
public class AK_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("Load bullets with AK");
    }
}
