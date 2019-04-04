package com.xym.jvm;

/**
 * 大对象直接进入老年代
 * <p>
 * VM参数：-XX:+UseParNewGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * <p>
 * 3145728为3MB
 *
 * @author xym
 * @create 2019-03-28 15:15
 */
public class GCAllocTest001 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //直接分配在老年代中
        byte[] allocation = new byte[4 * _1MB];
    }
}
