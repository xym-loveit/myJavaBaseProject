package com.xym.myJava.design_pattern.简单工厂模式;

/**
 * 正方形--图形实现类
 *
 * @author xym
 * @create 2018-12-04 11:13
 */
public class Square implements Shape {

    public Square() {
        System.out.println("Square");
    }

    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
