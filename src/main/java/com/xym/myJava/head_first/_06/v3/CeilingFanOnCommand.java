package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 15:04
 */
public class CeilingFanOnCommand implements Command {
    private CeilingFan ceilingFan;

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        this.ceilingFan.low();
    }

    @Override
    public void undo() {

    }
}
