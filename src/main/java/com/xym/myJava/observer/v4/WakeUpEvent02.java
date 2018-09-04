package com.xym.myJava.observer.v4;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:24
 */
public class WakeUpEvent02 {

    //醒来时间是否为饭点
    private boolean isFoodTime;
    //事件源
    private Baby04 source;

    public WakeUpEvent02(boolean isFoodTime, Baby04 source) {
        this.isFoodTime = isFoodTime;
        this.source = source;
    }

    public boolean isFoodTime() {
        return isFoodTime;
    }

    public void setFoodTime(boolean foodTime) {
        isFoodTime = foodTime;
    }

    public Baby04 getSource() {
        return source;
    }

    public void setSource(Baby04 source) {
        this.source = source;
    }
}
