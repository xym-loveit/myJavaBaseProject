package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.Command;
import com.xym.myJava.head_first._06.v2.GarageDoor;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 15:11
 */
public class GarageDoorUpCommand implements Command {
    private GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        this.garageDoor.up();
    }

    @Override
    public void undo() {

    }
}
