package com.xym.myJava.jmx.mbean;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-12 16:59
 */
public class HelloWorld implements HelloWorldMBean {

    private String greeting;

    public HelloWorld(String greeting) {
        this.greeting = greeting;
    }

    public HelloWorld() {
        this.greeting = "Hello World! I am Standard MBean";
    }

    @Override
    public void setGreeting(String greeting) {
        this.greeting = greeting;
        System.out.println("setGreeting==" + greeting);
    }

    @Override
    public String getGreeting() {
        System.out.println("getGreeting==" + greeting);
        return greeting;
    }

    @Override
    public String printGreeting() {
        System.out.println(greeting);
        return greeting;
    }
}
