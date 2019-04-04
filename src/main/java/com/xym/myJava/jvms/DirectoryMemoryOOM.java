package com.xym.myJava.jvms;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存oom
 * <p>
 * <p>
 * -XX:MaxDirectMemorySize=10M
 *
 * @author xym
 * @create 2019-03-26 22:53
 */
public class DirectoryMemoryOOM {
    public static final long _1MB = 1024 * 1024;

    public static void main(String[] args) {
        Field unsafe = Unsafe.class.getDeclaredFields()[0];
        unsafe.setAccessible(true);
        try {
            Unsafe unsafe1 = (Unsafe) unsafe.get(null);
            while (true) {
                unsafe1.allocateMemory(_1MB);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
