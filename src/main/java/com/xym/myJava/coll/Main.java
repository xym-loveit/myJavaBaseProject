package com.xym.myJava.coll;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-13 15:07
 */
public class Main {
    public static void main(String[] args) {
        ////计算大于等于number的2的幂数（2的几次方）
        //Integer.highestOneBit(num)只保留二进制的最高位的1，其余全为0
        System.out.println(Integer.highestOneBit((9 - 1) << 1));
        //左移就是乘以2
        System.out.println((16 - 1) << 1);
        System.out.println(Integer.toBinaryString((15 - 1) << 1));
        System.out.println(((15 - 1) << 1));
        speed();
    }

    private static void speed() {
        int hash = 30;
        int length = 16;

        int result3 = hash % length;
        int result4 = hash & (length - 1);
        System.out.println("result3=" + result3 + "，result4=" + result4);
        long start = System.currentTimeMillis();
        for (int x = 0; x < 100000; x++) {
            //int result1 = hash % length;
            int result2 = hash & (length - 1);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("共耗时：" + end);

    }
}
