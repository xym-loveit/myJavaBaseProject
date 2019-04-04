package com.xym.jvm;

/**
 * 长期存活的对象进入老年代
 * <p>
 * VM参数：-XX:+UseParNewGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
 * -XX:+PrintTenuringDistribution
 *
 * @author xym
 * @create 2019-03-28 15:15
 */
public class GCAllocTest002 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //直接分配在老年代中
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB * 4];
        allocation3 = new byte[_1MB * 4];
        allocation3 = null;
        allocation3 = new byte[_1MB * 4];

    }
}
