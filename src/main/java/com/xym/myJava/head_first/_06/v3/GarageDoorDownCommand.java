package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.Command;
import com.xym.myJava.head_first._06.v2.GarageDoor;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 15:12
 */
public class GarageDoorDownCommand implements Command {
    private GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        this.garageDoor.down();
    }

    @Override
    public void undo() {

    }
}
