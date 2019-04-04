package com.xym.myJava.jvms;

/**
 * 在同一个作用域内进行，不会进行gc
 *
 * @author xym
 * @create 2019-04-01 15:53
 */
public class SlotDemo03 {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }

        /**
         * 产生了新的局部变量，复用了placeholder占用的空间
         */
        int i = 0;
        System.gc();

        /**
         * [Full GC (System.gc())  66530K->895K(123904K), 0.0055170 secs]明显执行了gc，回收了内存
         */
    }
}
