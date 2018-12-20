package com.xym.myJava.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-18 16:17
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<HelloService> services = ServiceLoader.load(HelloService.class);
        Iterator<HelloService> iterator = services.iterator();
        while (iterator.hasNext()) {
            iterator.next().hello();
        }
    }
}
