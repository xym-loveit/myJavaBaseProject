package com.xym.myJava.head_first._06.v1;

/**
 * 具体command
 *
 * @author xym
 * @create 2019-04-11 10:30
 */
public class ConcreteCommand1 extends Command {

    //对哪个receiver类进行命令处理
    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;
    }

    //必须实现一个命令
    @Override
    public void execute() {
        System.out.println("命令对象...");
        //业务处理
        receiver.doSomething();
    }
}
