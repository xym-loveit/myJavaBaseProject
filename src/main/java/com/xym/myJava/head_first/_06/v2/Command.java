package com.xym.myJava.head_first._06.v2;

/**
 * 命令接口
 *
 * @author xym
 * @create 2019-04-11 11:20
 */
public interface Command {
    /**
     * 只有一个执行方法
     */
    void execute();

    /**
     * 撤销命令的方法
     */
    void undo();
}
