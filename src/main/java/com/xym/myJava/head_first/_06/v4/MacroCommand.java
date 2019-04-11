package com.xym.myJava.head_first._06.v4;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 17:05
 */
public class MacroCommand implements Command {

    //在宏命令中采用数组存放一大堆命令
    private Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        if (commands != null && commands.length > 0) {
            for (Command command : commands) {
                //当这个宏命令被执行时，一次性执行了数组里的所有命令
                command.execute();
            }
        }
    }

    @Override
    public void undo() {
        if (commands != null && commands.length > 0) {
            for (Command command : commands) {
                //当这个宏命令被执行时，一次性执行了数组里的所有命令
                command.undo();
            }
        }
    }
}
