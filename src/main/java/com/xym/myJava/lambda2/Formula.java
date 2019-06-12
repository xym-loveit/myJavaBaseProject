package com.xym.myJava.lambda2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 13:50
 */
public interface Formula {
    double calculate(int a);

    /**
     * 使用default定义默认方法
     *
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
