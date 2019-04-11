package com.xym.myJava.head_first._06.v4;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 17:09
 */
public class TV {
    private String location;

    public TV(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println("tv is on");
    }

    public void off() {
        System.out.println("tv is off");
    }
}
