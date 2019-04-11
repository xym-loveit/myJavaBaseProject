package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 每组具有开关功能的遥控器
 *
 * @author xym
 * @create 2019-04-11 14:21
 */
public class RemoteControlWithUndo {

    /**
     * 这个时候，遥控器需要处理7个开与关的命令
     * <p>
     * 使用相应的数组记录这些命令
     */
    Command[] onCommands;
    Command[] offCommands;
    /**
     * 用来记录前一步执行的命令
     */
    Command undoCommand;

    public RemoteControlWithUndo() {
        this.onCommands = new Command[7];
        this.offCommands = new Command[7];
        Command command = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = command;
            offCommands[i] = command;
        }
        //初始化时，没有前一个被执行的命令
        undoCommand = command;
    }

    /**
     * @param solt       插槽的位置
     * @param onCommand  开的命令
     * @param offCommand 关的命令
     */
    public void setCommand(int solt, Command onCommand, Command offCommand) {
        this.onCommands[solt] = onCommand;
        this.offCommands[solt] = offCommand;
    }

    /**
     * 按下某插槽开关按钮，执行响应的命令
     *
     * @param solt
     */
    public void onButtonWasPushed(int solt) {
        this.onCommands[solt].execute();
        undoCommand = this.onCommands[solt];
    }

    /**
     * 按下某插槽开关按钮，执行响应的命令
     *
     * @param slot
     */
    public void offButtonWasPushed(int slot) {
        this.offCommands[slot].execute();
        this.undoCommand = this.offCommands[slot];
    }

    /**
     * 当按下撤销按钮，调用undoCommand实例变量的undo方法，就可以倒转前一个命令
     */
    public void undoButtonWasPushed() {
        this.undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n---------Remote Control--------------\n");
        for (int i = 0; i < onCommands.length; i++) {
            buffer.append("[Slot " + i + "]").append(" ")
                    .append(onCommands[i].getClass().getName())
                    .append("    ")
                    .append(offCommands[i].getClass().getName())
                    .append("\n");
        }
        buffer.append("[undo]").append(" ").append(undoCommand.getClass().getName());
        return buffer.toString();
    }
}
