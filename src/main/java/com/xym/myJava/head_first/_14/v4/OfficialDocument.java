package com.xym.myJava.head_first._14.v4;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-22 20:24
 */
public interface OfficialDocument extends Cloneable {
    /**
     * clone方法
     *
     * @return
     */
    OfficialDocument clone();

    /**
     * 显示方法
     */
    void display();
}
