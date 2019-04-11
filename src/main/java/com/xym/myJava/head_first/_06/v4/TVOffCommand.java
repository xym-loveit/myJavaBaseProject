package com.xym.myJava.head_first._06.v4;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 17:17
 */
public class TVOffCommand implements Command {
    private TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.off();
    }

    @Override
    public void undo() {
        this.tv.on();
    }
}
