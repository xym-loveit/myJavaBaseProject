package com.xym.myJava;

import java.util.stream.IntStream;

/**
 * @author xym
 */
public class IntStreamDemo {
    public static void main(String[] args) {
        IntStream.range(10,20).forEach((i)-> System.out.println(i));
    }
}
