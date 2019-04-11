package com.xym.myJava.head_first._06.v4;

import com.xym.myJava.head_first._06.v2.Command;
import com.xym.myJava.head_first._06.v2.Light;
import com.xym.myJava.head_first._06.v2.LightOnCommand;
import com.xym.myJava.head_first._06.v3.*;

/**
 * 第三次重构--加入风扇的撤销动作
 *
 * @author xym
 * @create 2019-04-11 14:58
 */
public class RemoteLoader4 {
    public static void main(String[] args) {
        //遥控板
        RemoteControlWithUndo controlWithUndo = new RemoteControlWithUndo();
        //创建所有装置，电灯、电视、音响、热水器
        Light living_room = new Light("Living Room");
        TV tv = new TV("Living room");
        Stereo stereo = new Stereo("Living room");
        Hottub hottub = new Hottub();
        //创建所有的on/off命令来控制他们
        LightOnCommand lightOnCommand = new LightOnCommand(living_room);
        LightOffCommand lightOffCommand = new LightOffCommand(living_room);
        TVOnCommand tvOnCommand = new TVOnCommand(tv);
        TVOffCommand tvOffCommand = new TVOffCommand(tv);
        StereOffCommand stereOffCommand = new StereOffCommand(stereo);
        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        HottubOffCommand hottubOffCommand = new HottubOffCommand(hottub);
        HottubOnCommand hottubOnCommand = new HottubOnCommand(hottub);
        //创建2个命令数组，分别用来记录开启和关闭
        Command[] partyOn = {lightOnCommand, tvOnCommand, stereoOnWithCDCommand, hottubOnCommand};
        Command[] partyOff = {lightOffCommand, tvOffCommand, stereOffCommand, hottubOffCommand};
        //创建2个宏命令，特殊命令
        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);
        //将宏命令绑定到指定的按钮
        controlWithUndo.setCommand(0, partyOnMacro, partyOffMacro);
        System.out.println(controlWithUndo);
        System.out.println("----Pushing Macro On----");
        controlWithUndo.onButtonWasPushed(0);
        System.out.println("----Pushing Macro Off----");
        controlWithUndo.offButtonWasPushed(0);
    }
}
