package com.xym.myJava.head_first._14;

/**
 * 原型模式接口
 *
 * @author xym
 * @create 2019-04-22 14:44
 */
public interface Prototype {

    String getAttr();

    void setAttr(String attr);

    /**
     * 克隆方法
     *
     * @return
     */
    Prototype clone();
}
