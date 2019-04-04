package com.xym.myJava.jvms;

import java.util.Map;

/**
 * 查看虚拟机所有线程堆栈
 *
 * @author xym
 * @create 2019-03-29 14:03
 */
public class AllThreadTools {
    public static void main(String[] args) {
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
            Thread thread = (Thread) stackTrace.getKey();
            StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
            if (thread.equals(Thread.currentThread())) {
                continue;
            }
            System.out.println("线程：" + thread.getName());
            for (StackTraceElement stackTraceElement : stack) {
                System.out.println(stackTraceElement);
            }
        }
    }
}
