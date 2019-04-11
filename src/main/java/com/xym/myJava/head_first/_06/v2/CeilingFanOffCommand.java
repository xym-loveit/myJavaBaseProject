package com.xym.myJava.head_first._06.v2;

import com.xym.myJava.head_first._06.v3.CeilingFan;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 15:10
 */
public class CeilingFanOffCommand implements Command {
    private CeilingFan ceilingFan;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        this.ceilingFan.off();
    }

    @Override
    public void undo() {

    }
}
