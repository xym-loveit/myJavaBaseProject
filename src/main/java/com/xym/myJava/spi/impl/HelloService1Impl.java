package com.xym.myJava.spi.impl;

import com.xym.myJava.spi.HelloService;

/**
 * SPI服务接口实现类
 *
 * @author xym
 * @create 2018-12-18 16:15
 */
public class HelloService1Impl implements HelloService {
    @Override
    public void hello() {
        System.out.println("hello1");
    }
}
