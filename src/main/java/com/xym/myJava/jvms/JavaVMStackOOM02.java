package com.xym.myJava.jvms;

/**
 * 模拟栈内存溢出（限定栈内存空间，创建比较多的线程，方法调用对应着栈帧的入栈和出栈过程）
 * <p>
 * Hotspot虚拟机的虚拟机栈和本地方法栈是一个
 *
 * <p>
 * -Xss2M
 *
 * @author xym
 * @create 2019-03-26 13:40
 */
public class JavaVMStackOOM02 {
    private int stackLength = 1;


    private void dontStop() {
        while (true) {

        }
    }

    private void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(() -> {
                dontStop();
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM02 javaVMStackOOM = new JavaVMStackOOM02();
        try {
            javaVMStackOOM.stackLeakByThread();
        } catch (Throwable e) {
            System.out.println("stackLength=" + javaVMStackOOM.stackLength);
            throw e;
        }
    }
}
