package com.xym.myJava.JMM;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串常量引起的内存溢出：
 * <p>
 * 在jdk1.6/1.7/1.8中的表现会有不同
 * <p>
 * 1.7/1.8
 * java.lang.OutOfMemoryError: Java heap space
 * <p>
 * -XX:PermSize=8MB -XX:MaxPermSize=8MB
 * <p>
 * 1.8 元空间
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * @author xym
 * @create 2018-11-30 15:_01
 */
public class StringOomMock {
    static String base = "string";

    public static void main(String[] args) {
        //以2的指数级不断的生成新的字符串
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
