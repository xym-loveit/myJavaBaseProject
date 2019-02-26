package com.xym.myJava.args_pass;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-20 14:34
 */
public class Main {

    private static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash

    public static void main(String[] args) {
        System.out.println(tableSizeFor(20));
        System.out.println(HASH_BITS);
        System.out.println(Integer.MAX_VALUE);
    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 100) ? MAXIMUM_CAPACITY : n + 1;
    }
}
