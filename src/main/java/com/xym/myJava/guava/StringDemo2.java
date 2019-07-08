package com.xym.myJava.guava;

import com.google.common.base.Strings;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-04 11:36
 */
public class StringDemo2 {
    public static void main(String[] args) {
        System.out.println(Strings.commonPrefix("vvsddf", "vv2"));
        System.out.println(Strings.commonSuffix("124.txt", "345.txt"));
        System.out.println(Strings.emptyToNull(""));
        System.out.println(Strings.emptyToNull(null));
    }
}
