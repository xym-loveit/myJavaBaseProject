package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.CeilingFanOffCommand;
import com.xym.myJava.head_first._06.v2.GarageDoor;
import com.xym.myJava.head_first._06.v2.Light;
import com.xym.myJava.head_first._06.v2.LightOnCommand;

/**
 * 初步版本实现了多个插槽按钮控制灯的开关
 *
 * @author xym
 * @create 2019-04-11 14:58
 */
public class RemoteLoader {
    public static void main(String[] args) {
        //遥控器
        RemoteControl control = new RemoteControl();
        //灯
        Light livingRoomLight = new Light("living room");
        Light kitchenLight = new Light("kitchen room");
        //风扇
        CeilingFan ceilingFan = new CeilingFan("Living room");
        //车库门
        GarageDoor garageDoor = new GarageDoor();
        //音响
        Stereo stereo = new Stereo("living room");

        //电灯的开关命令对象
        LightOnCommand lightOnCommand = new LightOnCommand(livingRoomLight);
        LightOffCommand lightOffCommand = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        //吊扇的命令开关
        CeilingFanOnCommand ceilingFanOnCommand = new CeilingFanOnCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);
        //车库门的上下
        GarageDoorUpCommand upComman = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand downComman = new GarageDoorDownCommand(garageDoor);
        //音响开关命令
        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        StereOffCommand stereOffCommand = new StereOffCommand(stereo);

        control.setCommand(0, lightOnCommand, lightOffCommand);
        control.setCommand(1, kitchenLightOn, kitchenLightOff);
        control.setCommand(2, ceilingFanOnCommand, ceilingFanOffCommand);
        //control.setCommand(3, upComman, downComman);
        control.setCommand(3, stereoOnWithCDCommand, stereOffCommand);

        //打印每个插槽及被安插的命令
        System.out.println(control);

        //逐步按下每个插槽的开与关按钮
        control.onButtonWasPushed(0);
        control.offButtonWasPushed(0);
        control.onButtonWasPushed(1);
        control.offButtonWasPushed(1);
        control.onButtonWasPushed(2);
        control.offButtonWasPushed(2);
        control.onButtonWasPushed(3);
        control.offButtonWasPushed(3);
    }
}
