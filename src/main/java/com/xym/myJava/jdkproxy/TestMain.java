package com.xym.myJava.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理示例
 *
 * @author xym
 * @create 2018-09-03 16:01
 */
public class TestMain {
    public static void main(String[] args) {

        PersonInvocationHandler personInvocationHandler = new PersonInvocationHandler(new PersonImpl());
        Person person
                = (Person) Proxy.newProxyInstance(PersonImpl.class.getClassLoader(), PersonImpl.class.getInterfaces(), personInvocationHandler);
        person.eat();
    }
}
