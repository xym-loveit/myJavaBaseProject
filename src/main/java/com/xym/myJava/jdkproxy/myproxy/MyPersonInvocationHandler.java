package com.xym.myJava.jdkproxy.myproxy;

import java.lang.reflect.Method;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 16:15
 */
public class MyPersonInvocationHandler implements MyInvocationHandler {

    private Object obj;

    public MyPersonInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before time to eat");
        method.invoke(obj, args);
        System.out.println("after time to eat");
        return null;
    }
}
