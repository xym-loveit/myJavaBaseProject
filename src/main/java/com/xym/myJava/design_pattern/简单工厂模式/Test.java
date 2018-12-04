package com.xym.myJava.design_pattern.简单工厂模式;

/**
 * 测试客户端
 *
 * @author xym
 * @create 2018-12-04 11:14
 */
public class Test {
    public static void main(String[] args) {
        // 获取 Circle 的对象，并调用它的 draw 方法
        Shape circle = ShapeFactory.getShape("CIRCLE");
        circle.draw();

        // 获取 Rectangle 的对象，并调用它的 draw 方法
        Shape rectangle = ShapeFactory.getShape("RECTANGLE");
        rectangle.draw();

        // 获取 Square 的对象，并调用它的 draw 方法
        Shape square = ShapeFactory.getShape("SQUARE");
        square.draw();

    }
}
