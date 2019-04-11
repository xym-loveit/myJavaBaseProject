package com.xym.myJava.head_first._06.v2;

/**
 * 电灯类
 *
 * @author xym
 * @create 2019-04-11 11:21
 */
public class Light {

    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is off");
    }
}
