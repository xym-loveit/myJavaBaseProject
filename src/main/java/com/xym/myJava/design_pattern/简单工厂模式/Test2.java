package com.xym.myJava.design_pattern.简单工厂模式;

/**
 * 利用反射解决简单工厂每次增加新了产品类都要修改产品工厂的弊端-----------客户端测试
 * <p>
 * <p>
 * 如果需要改善的话可以通过 反射+配置文件 的形式来改善
 *
 * @author xym
 * @create 2018-12-04 11:26
 */
public class Test2 {
    public static void main(String[] args) {
        Circle circle = (Circle) ShapeFactory2.getClass(Circle.class);
        circle.draw();

        Rectangle rectangle = (Rectangle) ShapeFactory2.getClass(Rectangle.class);
        rectangle.draw();

        Square square = (Square) ShapeFactory2.getClass(Square.class);
        square.draw();

    }
}
