package com.xym.myJava.head_first._03;

/**
 * 调料装饰者--抽象装饰者类
 * <p>
 * 注意类型和被装饰者类型一致
 *
 * @author xym
 * @create 2019-04-09 16:17
 */
public abstract class CondimentDecorator extends Beverage {
    /**
     * 所有饮料装饰者都必须实现
     *
     * @return
     */
    @Override
    public abstract String getDescription();
}
