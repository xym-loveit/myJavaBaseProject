package com.xym.myJava;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-03 17:15
 */
public class MyTest2 {


    @Test
    public void testA() {
        System.out.println("~~~~~~~~~~~~~~~~~~~`T2---A");
    }

    @Test
    public void testB() {
        System.out.println("~~~~~~~~~~~~~~~~~~~`T2---B");
    }

    /*忽略该测试*/
    @Ignore
    public void testC() {
        System.out.println("~~~~~~~~~~~~~~~~~~~`T2---C");
    }
}
