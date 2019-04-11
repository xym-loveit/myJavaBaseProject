package com.xym.myJava.head_first._06.v2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-11 11:21
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.on();
    }

    @Override
    public void undo() {
        this.light.off();
    }
}
