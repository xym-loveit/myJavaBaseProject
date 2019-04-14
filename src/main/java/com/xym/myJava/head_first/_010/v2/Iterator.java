package com.xym.myJava.head_first._010.v2;

/**
 * 迭代器接口------将不同的集合遍历的实现隐藏，对外提供统一的接口
 *
 * @author xym
 * @create 2019-04-14 22:44
 */
public interface Iterator {
    /**
     * 是否还有元素
     *
     * @return
     */
    boolean hasNext();

    /**
     * 取得下一个元素
     *
     * @return
     */
    Object next();

}
