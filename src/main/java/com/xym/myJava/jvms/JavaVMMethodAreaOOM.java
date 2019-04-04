package com.xym.myJava.jvms;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 通过cglib产生大量类，占用方法区（1.7之前）
 * <p>
 * -XX:MetaspaceSize=5m
 * -XX:MaxMetaspaceSize=5m
 *
 * @author xym
 * @create 2019-03-26 16:56
 */
public class JavaVMMethodAreaOOM {
    public static void main(String[] args) {

        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
        }

    }

    static class OOMObject {

    }
}
