package com.xym.myJava.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-12 16:28
 */
public class MXBeanDemo {
    public static void main(String[] args) {
        /**
         * 通过ThreadMXBean，打印当前jvm实例中所有的线程
         */
        System.out.println("below is thread info:");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] allThreadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(allThreadIds);
        Arrays.stream(threadInfo).forEach(t -> {
            System.out.println(t);
        });
    }
}
