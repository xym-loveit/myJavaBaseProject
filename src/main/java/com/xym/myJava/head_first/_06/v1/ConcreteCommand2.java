package com.xym.myJava.head_first._06.v1;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 10:32
 */
public class ConcreteCommand2 extends Command {

    //对哪个receiver进行命令处理
    private Receiver receiver;

    public ConcreteCommand2(Receiver receiver) {
        this.receiver = receiver;
    }

    //必须实现一个命令
    @Override
    public void execute() {
        //业务处理
        this.receiver.doSomething();
    }
}
