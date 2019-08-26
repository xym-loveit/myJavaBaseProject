package com.xym.myJava.thread.cnblogs_skywang12345.chapter2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-24 18:46
 */
public class Test01 {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
        return 42 == ACCESS_ALLOWED;
    }
}
