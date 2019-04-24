package com.xym.myJava.head_first._016.v2;

/**
 * 坐标类，充当享元模式中变化的外部状态
 *
 * @author xym
 * @create 2019-04-24 11:44
 */
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
