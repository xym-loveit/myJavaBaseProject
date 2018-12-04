package com.xym.myJava.design_pattern.工厂方法;

import com.xym.myJava.design_pattern.简单工厂模式.Rectangle;
import com.xym.myJava.design_pattern.简单工厂模式.Shape;

/**
 * 长方形工厂类
 *
 * @author xym
 * @create 2018-12-04 11:29
 */
public class RectangleFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Rectangle();
    }
}
