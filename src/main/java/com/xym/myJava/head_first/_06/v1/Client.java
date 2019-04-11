package com.xym.myJava.head_first._06.v1;

/**
 * 场景类--客户端
 *
 * @author xym
 * @create 2019-04-11 10:34
 */
public class Client {
    public static void main(String[] args) {
        //实例化调用者
        Invoker invoker = new Invoker();
        //实例化接受者
        ConcreteReciver1 reciver1 = new ConcreteReciver1();
        //将接受者绑定到命令对象
        ConcreteCommand1 command1 = new ConcreteCommand1(reciver1);
        //将命令对象绑定到调用者
        invoker.setCommand(command1);
        //执行调用
        invoker.action();
    }
}
