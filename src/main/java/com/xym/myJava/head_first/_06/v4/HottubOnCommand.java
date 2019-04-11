package com.xym.myJava.head_first._06.v4;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 17:12
 */
public class HottubOnCommand implements Command {
    private Hottub hottub;

    public HottubOnCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    @Override
    public void execute() {
        this.hottub.on();
    }

    @Override
    public void undo() {
        this.hottub.off();
    }
}
