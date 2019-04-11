package com.xym.myJava.head_first._06.v1;

/**
 * 调用者Invoker
 *
 * @author xym
 * @create 2019-04-11 10:33
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        System.out.println("调用者...");
        this.command.execute();
    }
}
