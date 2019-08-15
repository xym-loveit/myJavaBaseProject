package com.xym.myJava.jmx.mbean;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-14 15:14
 */
public interface HelloMBean {
    void sayHello();

    int add(int x, int y);

    String getName();

    int getCacheSize();

    void setCacheSize(int size);
}
