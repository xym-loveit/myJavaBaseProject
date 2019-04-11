package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.GarageDoor;
import com.xym.myJava.head_first._06.v2.Light;
import com.xym.myJava.head_first._06.v2.LightOnCommand;

/**
 * 第二个版本--加入了电灯开关的撤销动作
 *
 * @author xym
 * @create 2019-04-11 14:58
 */
public class RemoteLoader2 {
    public static void main(String[] args) {
        //遥控器
        RemoteControlWithUndo control = new RemoteControlWithUndo();
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

        control.setCommand(0, lightOnCommand, lightOffCommand);

        //逐步按下每个插槽的开与关按钮
        control.onButtonWasPushed(0);
        control.offButtonWasPushed(0);

        //打印每个插槽及被安插的命令
        System.out.println(control);
        //撤销上一步操作后为on状态
        control.undoButtonWasPushed();
        //逐步按下每个插槽的开与关按钮
        control.offButtonWasPushed(0);
        control.onButtonWasPushed(0);
        System.out.println(control);
        control.undoButtonWasPushed();

    }
}
