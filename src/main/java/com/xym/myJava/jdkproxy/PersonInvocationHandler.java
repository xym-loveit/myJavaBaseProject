package com.xym.myJava.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 15:59
 */
public class PersonInvocationHandler implements InvocationHandler {

    private Object target;

    public PersonInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before time to eat");
        method.invoke(target, args);
        System.out.println("after time to eat");
        return null;
    }
}
