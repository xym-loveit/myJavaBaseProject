package com.xym.myJava.jmx;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-12 16:57
 */
public interface HelloWorldMBean {

    void setGreeting(String greeting);

    String getGreeting();

    String printGreeting();
}
