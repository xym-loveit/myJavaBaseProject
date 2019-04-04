package com.xym.myJava.jvms;

import java.util.ArrayList;

/**
 * VM args：-Xmx10m -Xms10m -XX:+UseSerialGC
 *
 * @author xym
 * @create 2019-03-29 14:25
 */
public class JConsoleDemo {
    /**
     * 内存占位符，一个OOMObject对象大约64kb
     */
    static class OOMObject {

        public byte[] placeholder = new byte[64 * 1024];
    }


    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }

    private static void fillHeap(int num) throws InterruptedException {
        ArrayList<OOMObject> arrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //稍作延迟，令监视曲线的变化更加明显
            Thread.sleep(50);
            arrayList.add(new OOMObject());
        }
        System.gc();
    }
}
