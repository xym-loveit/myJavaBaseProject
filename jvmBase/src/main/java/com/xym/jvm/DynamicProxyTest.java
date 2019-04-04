package com.xym.jvm;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-02 16:49
 */
public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements java.lang.reflect.InvocationHandler {

        private Object targetObj;

        Object bind(Object targetObj) {
            this.targetObj = targetObj;
            return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(targetObj, args);
        }
    }

    public static void main(String[] args) {
        //System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFile", "true");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello proxy = (IHello) new DynamicProxy().bind(new Hello());
        proxy.sayHello();
    }
}
