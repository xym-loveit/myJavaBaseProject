package com.xym.myJava.jdkproxy.myproxy;

import java.lang.reflect.Method;

/**
 * 抄袭JDK InvocationHandler接口
 *
 * @author xym
 * @create 2018-09-03 16:12
 */
public interface MyInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
