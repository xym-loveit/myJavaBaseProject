package com.xym.myJava.guava;

import com.google.common.hash.Hashing;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-04 11:32
 */
public class HashDemo {
    public static void main(String[] args) {
        String input = "hello, world";
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.md5().hashUnencodedChars(input).toString());
    }
}
