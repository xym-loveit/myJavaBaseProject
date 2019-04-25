package com.xym.myJava.head_first._11.v1;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-16 16:43
 */
public interface State {
    /**
     * 投钱动作
     */
    void insertQuarter();

    /**
     * 退钱
     */
    void ejectQuarter();

    /**
     * 转动曲柄，要糖果
     */
    void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();

}