package com.xym.myJava.head_first._06.v1;

/**
 * 具体的接受者，真正干事的
 *
 * @author xym
 * @create 2019-04-11 10:26
 */
public class ConcreteReciver1 extends Receiver {
    //每个接受者都必须处理一定得业务逻辑
    @Override
    public void doSomething() {
        System.out.println("接受者真正处理业务...");
    }
}
