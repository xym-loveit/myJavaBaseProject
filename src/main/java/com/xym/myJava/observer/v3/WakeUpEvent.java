package com.xym.myJava.observer.v3;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:24
 */
public class WakeUpEvent {

    //醒来时间是否为饭点
    private boolean isFoodTime;
    //事件源
    private Baby03 source;

    public WakeUpEvent(boolean isFoodTime, Baby03 source) {
        this.isFoodTime = isFoodTime;
        this.source = source;
    }

    public boolean isFoodTime() {
        return isFoodTime;
    }

    public void setFoodTime(boolean foodTime) {
        isFoodTime = foodTime;
    }

    public Baby03 getSource() {
        return source;
    }

    public void setSource(Baby03 source) {
        this.source = source;
    }
}
