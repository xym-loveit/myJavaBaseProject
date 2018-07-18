package com.xym.myJava.latch;

/**
 * 程序员出游类
 *
 * @author xym
 * @create 2018-07-18 18:00
 */
public class ProgrammerTravel extends Thread {

    /**
     * 门栓
     */
    private final Latch latch;

    /**
     * 程序员
     */
    private final String programmer;

    /**
     * 交通工具
     */
    private final String transportaion;

    public ProgrammerTravel(Latch latch, String programmer, String transportaion) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportaion = transportaion;
    }
}
