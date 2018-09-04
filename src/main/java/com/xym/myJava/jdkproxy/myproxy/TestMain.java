package com.xym.myJava.jdkproxy.myproxy;

import com.xym.myJava.jdkproxy.Person;
import com.xym.myJava.jdkproxy.PersonImpl;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 16:26
 */
public class TestMain {
    public static void main(String[] args) {
        MyPersonInvocationHandler personInvocationHandler = new MyPersonInvocationHandler(new PersonImpl());
        Person person
                = (Person) MyProxy.newProxyInstance(new MyClassLoader(), PersonImpl.class.getInterfaces(), personInvocationHandler);
        person.eat();
    }
}
