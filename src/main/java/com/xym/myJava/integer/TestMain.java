package com.xym.myJava.integer;

import java.util.Properties;

public class TestMain {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        props.put("hollis.integer.test.key","10000");
        Integer i = Integer.getInteger("hollis.integer.test.key");
        System.out.println(i);
    }
}
