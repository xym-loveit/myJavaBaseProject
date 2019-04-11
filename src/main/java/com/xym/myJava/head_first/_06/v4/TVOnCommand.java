package com.xym.myJava.head_first._06.v4;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 17:11
 */
public class TVOnCommand implements Command {
    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.on();
    }

    @Override
    public void undo() {
        this.tv.off();
    }
}
