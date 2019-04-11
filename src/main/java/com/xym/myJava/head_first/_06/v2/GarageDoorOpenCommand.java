package com.xym.myJava.head_first._06.v2;

/**
 * 车库门打开命令
 *
 * @author xym
 * @create 2019-04-11 11:31
 */
public class GarageDoorOpenCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
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
