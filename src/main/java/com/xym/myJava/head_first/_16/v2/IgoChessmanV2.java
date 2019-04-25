package com.xym.myJava.head_first._16.v2;

/**
 * 围棋棋子类，抽象享元类
 *
 * @author xym
 * @create 2019-04-24 11:45
 */
public abstract class IgoChessmanV2 {
    public abstract String getColor();

    /**
     * 将外部的变化状态传入
     *
     * @param coordinates
     */
    public void display(Coordinates coordinates) {
        System.out.println("棋子的颜色：" + getColor() + "，棋子的位置：" + coordinates.getX() + "," + coordinates.getY());
    }
}
