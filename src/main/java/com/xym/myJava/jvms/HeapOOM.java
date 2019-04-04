package com.xym.myJava.jvms;

import java.util.ArrayList;

/**
 * 模拟堆内存溢出(定死堆内存大小，使其无法扩张，并创建大量的对象)
 * <p>
 * <p>
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author xym
 * @create 2019-03-26 10:58
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<OOMObject> objects = new ArrayList<>();
        int i = 0;
        while (true) {
            if (i == 10000) {
                break;
            }
            objects.add(new OOMObject());
            i++;
        }
        objects = null;
        System.out.println("开始gc...");
        System.gc();
        Thread.sleep(10000000);
    }
}
