package com.xym.myJava.head_first._06.v2;

/**
 * 遥控器类
 *
 * @author xym
 * @create 2019-04-11 11:23
 */
public class SimpleRemoteControl {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonWasPressed() {
        this.command.execute();
    }

}
