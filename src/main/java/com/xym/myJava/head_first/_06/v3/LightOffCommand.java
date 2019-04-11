package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.Command;
import com.xym.myJava.head_first._06.v2.Light;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 14:45
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }

    @Override
    public void undo() {
        this.light.on();
    }
}
