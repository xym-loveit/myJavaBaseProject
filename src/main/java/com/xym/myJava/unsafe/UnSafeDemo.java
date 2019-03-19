package com.xym.myJava.unsafe;

import sun.misc.Unsafe;

/**
 * Unsafe是给bootstrap加载器使用的,普通调用会抛异常
 *
 * @author xym
 * @create 2019-03-14 15:57
 */
public class UnSafeDemo {
    public static void main(String[] args) {
        //返回指定的变量在所属类中的内存偏移地址,只能执行由根加载器加载的类
        Unsafe unsafe = Unsafe.getUnsafe();
        //try {
        //long value = unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
        //System.out.println(value);
        //} catch (NoSuchFieldException e) {
        //    e.printStackTrace();
        //}

        int array[] = new int[5];
        //获取数组中第一个元素的地址
        //unsafe.arrayBaseOffset(array.getClass());
        //获取数组中第一个元素占用的字节
        //unsafe.arrayIndexScale(array.getClass());
        //比较对象obj中偏移量为offset的变量的值是否与expect相等，相等则使用update值更新，然后返回true，否则返回false
        //unsafe.compareAndSwapLong();

    }
}
