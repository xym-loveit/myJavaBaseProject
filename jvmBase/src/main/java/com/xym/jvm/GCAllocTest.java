package com.xym.jvm;

/**
 * 对象优先在Eden中的分配
 * <p>
 * <p>
 * JVM参数：-XX:+UseParNewGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * @author xym
 * @create 2019-03-28 13:39
 */
public class GCAllocTest {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] _alloc1, _alloc2, _alloc3, _alloc4;
        _alloc1 = new byte[2 * _1MB];
        _alloc2 = new byte[2 * _1MB];
        _alloc3 = new byte[2 * _1MB];
        //出现一次minor GC
        _alloc4 = new byte[4 * _1MB];
    }
}
