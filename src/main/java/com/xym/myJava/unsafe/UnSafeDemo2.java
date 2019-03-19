package com.xym.myJava.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 采用反射调用unsafe类
 *
 * @author xym
 * @create 2019-03-14 16:16
 */
public class UnSafeDemo2 {

    private volatile long state = 0;
    private long noVolatile = 0;
    private static long stateOffset = 0;
    private static long noVolatileOffset = 0;

    static Unsafe UNSAFE = null;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            //变量state在所属类UnSafeDemo2中的内存偏移地址
            long state1 = UNSAFE.objectFieldOffset(UnSafeDemo2.class.getDeclaredField("state"));
            long state2 = UNSAFE.objectFieldOffset(UnSafeDemo2.class.getDeclaredField("noVolatile"));
            stateOffset = state1;
            noVolatileOffset = state2;
            System.out.println(state1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static int[] array = new int[]{1, 2, 3, 4, 5};
    static long[] array2 = new long[]{1L, 2L, 3L, 4L, 5L};
    static String[] array3 = new String[]{"我", "b", "c"};


    public static void main(String[] args) {
        UnSafeDemo2 unSafeDemo2 = new UnSafeDemo2();
        boolean isSuccess = UNSAFE.compareAndSwapLong(unSafeDemo2, stateOffset, 0, 2);
        boolean isSuccess2 = UNSAFE.compareAndSwapLong(unSafeDemo2, stateOffset, 3, 6);

        int i = UNSAFE.arrayBaseOffset(array.getClass());
        System.out.println("数组array中第一个元素的地址" + i);
        int scale = UNSAFE.arrayIndexScale(array.getClass());
        int scale2 = UNSAFE.arrayIndexScale(array2.getClass());
        int scale3 = UNSAFE.arrayIndexScale(array3.getClass());
        System.out.println("数组array中第一个元素占用的字节" + scale);
        System.out.println("数组array2中第一个元素占用的字节" + scale2);
        System.out.println("数组array3中第一个元素占用的字节" + scale3);
        System.out.println(isSuccess);
        System.out.println(isSuccess2);
        System.out.println(unSafeDemo2.state);
        System.out.println(unSafeDemo2.state);

        long aVolatile = UNSAFE.getLongVolatile(unSafeDemo2, stateOffset);
        long aVolatile2 = UNSAFE.getLongVolatile(unSafeDemo2, noVolatileOffset);
        System.out.println("对象unSafeDemo2中偏移量为stateOffset的变量对应的volatile语义的值：" + aVolatile);
        System.out.println("对象unSafeDemo2中偏移量为noVolatileOffset的变量对应的volatile语义的值：" + aVolatile2);
    }
}
