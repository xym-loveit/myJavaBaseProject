package com.xym.myJava.workThread;

/**
 * 流水线的产品说明书
 *
 * @author xym
 * @create 2018-07-19 17:00
 */
public abstract class InstructionBook {

    /**
     * 加工产品的方法
     */
    public final void create() {
        this.firstProcess();
        this.secondProcess();
    }

    /**
     * 加工工序1
     */
    protected abstract void firstProcess();

    /**
     * 加工工序2
     */
    protected abstract void secondProcess();

}
