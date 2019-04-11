package com.xym.myJava.head_first._06.v3;

/**
 * 第三次重构--加入风扇的撤销动作
 *
 * @author xym
 * @create 2019-04-11 14:58
 */
public class RemoteLoader3 {
    public static void main(String[] args) {
        //遥控器
        RemoteControlWithUndo control = new RemoteControlWithUndo();
        //风扇
        CeilingFan ceilingFan = new CeilingFan("Living room");
        //实例化三个命令，分别是高、中、低、关闭
        CeilingFanHighCommand fanHighCommand = new CeilingFanHighCommand(ceilingFan);
        CeilingFanMediumCommand ceilingFanMediumCommand = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);

        control.setCommand(0, ceilingFanMediumCommand, ceilingFanOffCommand);
        control.setCommand(1, fanHighCommand, ceilingFanOffCommand);

        //首先中速开启，然后关闭
        control.onButtonWasPushed(0);
        control.offButtonWasPushed(0);
        System.out.println(control);
        //撤销后变为中速
        control.undoButtonWasPushed();
        control.onButtonWasPushed(1);
        System.out.println(control);
        //再一次撤销回到中速
        control.undoButtonWasPushed();
    }
}
