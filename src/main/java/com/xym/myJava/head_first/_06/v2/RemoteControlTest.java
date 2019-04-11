package com.xym.myJava.head_first._06.v2;

/**
 * 遥控器测试类
 *
 * @author xym
 * @create 2019-04-11 11:25
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        //实例化遥控器，即调用者
        SimpleRemoteControl control = new SimpleRemoteControl();
        //实例化电灯，即接受者
        Light light = new Light("");
        //实例化开灯命令，即命令对象
        LightOnCommand lightOnCommand = new LightOnCommand(light);


        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand doorOpenCommand = new GarageDoorOpenCommand(garageDoor);

        //将命令对象绑定在调用者上
        control.setCommand(lightOnCommand);
        //调用调用者的功能方法
        control.buttonWasPressed();
        control.setCommand(doorOpenCommand);
        control.buttonWasPressed();
    }
}
