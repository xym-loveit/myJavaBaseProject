package com.xym.myJava.head_first._16;

/**
 * 围棋棋子类：抽象享元类
 *
 * @author xym
 * @create 2019-04-24 11:27
 */
public abstract class IgoChessman {

    public abstract String getColor();

    public void display() {
        System.out.println("棋子颜色：" + this.getColor());
    }
}
