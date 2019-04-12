package com.xym.myJava.head_first._08;

/**
 * 门面类（核心）
 *
 * @author xym
 * @create 2019-04-12 11:49
 */
public class Computer {
    private CPU cpu;
    private Disk disk;
    private Memory memory;

    public Computer() {
        this.cpu = new CPU();
        this.disk = new Disk();
        this.memory = new Memory();
    }

    public void start() {
        System.out.println("Computer start begin");
        cpu.start();
        disk.start();
        memory.start();
        System.out.println("Computer start end");
    }

    public void shutDown() {
        System.out.println("Computer shutDown begin");
        cpu.shutdown();
        disk.shutdown();
        memory.shutdown();
        System.out.println("Computer shutDown end...");
    }
}
