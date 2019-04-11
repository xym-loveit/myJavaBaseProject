package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 具有撤销速度功能的风扇
 *
 * @author xym
 * @create 2019-04-11 16:47
 */
public class CeilingFanMediumCommand implements Command {

    private CeilingFan ceilingFan;
    //上一次速度（未变化之前速度）
    int prevSpeed;


    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        //在我们改变吊扇速度之前需要将之前的速度记录下来以便撤销时使用
        this.prevSpeed = this.ceilingFan.getSpeed();
        this.ceilingFan.medium();
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            this.ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            this.ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            this.ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            this.ceilingFan.off();
        }
    }
}
