package com.xym.myJava.rpc;

import java.lang.reflect.Proxy;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 17:00
 */
public class RpcConsumer {

    public static <T> T getService(Class<T> clazz, String ip, int port) {
        MyProxyHandler proxyHandler = new MyProxyHandler(ip, port);
        return (T) Proxy.newProxyInstance(RpcConsumer.class.getClassLoader(), new Class<?>[]{clazz}, proxyHandler);
    }
}
