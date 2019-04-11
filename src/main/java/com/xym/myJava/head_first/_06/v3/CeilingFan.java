package com.xym.myJava.head_first._06.v3;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 14:54
 */
public class CeilingFan {
    /**
     * 风扇的位置
     */
    private String location;

    /**
     * 风速
     */
    private int speed;

    /**
     * 风扇的风力等级
     */
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    public CeilingFan(String location) {
        this.location = location;
        this.speed = OFF;
    }

    public void high() {
        speed = HIGH;
        System.out.println(location + " ceiling fan is on high");
    }

    public void medium() {
        speed = MEDIUM;
        System.out.println(location + " ceililng fan is on medium");
    }

    public void low() {
        speed = LOW;
        System.out.println(location + " ceiling fan is on low");
    }

    /**
     * 关闭吊扇
     */
    public void off() {
        speed = OFF;
        System.out.println(location + " ceiling fan is on off");
    }

    public int getSpeed() {
        return speed;
    }
}
