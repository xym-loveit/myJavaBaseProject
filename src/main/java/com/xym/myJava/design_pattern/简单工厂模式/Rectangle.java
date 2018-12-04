package com.xym.myJava.design_pattern.简单工厂模式;

/**
 * 长方形--图形实现类
 *
 * @author xym
 * @create 2018-12-04 11:12
 */
public class Rectangle implements Shape {

    public Rectangle() {
        System.out.println("Rectangle");
    }

    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}
