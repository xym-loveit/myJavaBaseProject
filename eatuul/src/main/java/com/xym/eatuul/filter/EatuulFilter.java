package com.xym.eatuul.filter;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-05 17:40
 */
public interface EatuulFilter {

    /**
     * 过滤器类型
     *
     * @return
     */
    abstract public String filterType();

    /**
     * 过滤器顺序
     *
     * @return
     */
    abstract public int filterOrder();

    /**
     * 过滤器执行逻辑
     */
    abstract public void run();

}
