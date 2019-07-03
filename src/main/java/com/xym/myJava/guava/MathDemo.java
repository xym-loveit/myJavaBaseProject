package com.xym.myJava.guava;

import com.google.common.math.IntMath;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-03 17:52
 */
public class MathDemo {
    public static void main(String[] args) {
        //System.out.println(IntMath.binomial(16, 3));
        //返回大于或等于x的最小2的幂.
        System.out.println(IntMath.ceilingPowerOfTwo(6));
        System.out.println(IntMath.ceilingPowerOfTwo(16));
        //阶乘
        //System.out.println(IntMath.factorial(12));
        //返回小于或等于x的最大2的幂。
        System.out.println(IntMath.floorPowerOfTwo(5));

    }
}
