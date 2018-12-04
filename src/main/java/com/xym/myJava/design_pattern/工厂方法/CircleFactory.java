package com.xym.myJava.design_pattern.工厂方法;

import com.xym.myJava.design_pattern.简单工厂模式.Circle;
import com.xym.myJava.design_pattern.简单工厂模式.Shape;

/**
 * 圆形工厂类
 *
 * @author xym
 * @create 2018-12-04 11:28
 */
public class CircleFactory implements Factory {

    @Override
    public Shape getShape() {
        return new Circle();
    }
}
