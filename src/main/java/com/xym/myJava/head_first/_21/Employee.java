package com.xym.myJava.head_first._21;

/**
 * 员工类：抽象元素类
 *
 * @author xym
 * @create 2019-04-25 15:39
 */
public interface Employee {
    /**
     * 接受一个抽象访问者访问
     *
     * @param handler
     */
    void accept(Department handler);
}
