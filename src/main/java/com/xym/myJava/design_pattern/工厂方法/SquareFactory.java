package com.xym.myJava.design_pattern.工厂方法;

import com.xym.myJava.design_pattern.简单工厂模式.Shape;
import com.xym.myJava.design_pattern.简单工厂模式.Square;

/**
 * 圆形工厂类
 *
 * @author xym
 * @create 2018-12-04 11:30
 */
public class SquareFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
