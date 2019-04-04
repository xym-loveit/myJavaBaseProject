package com.xym.myJava.jvms;

/**
 * 模拟栈内存溢出（限定栈内存空间，并递归调用方法，方法调用对应着栈帧的入栈和出栈过程）
 * <p>
 * Hotspot虚拟机的虚拟机栈和本地方法栈是一个
 *
 * <p>
 * -Xss128k
 *
 * @author xym
 * @create 2019-03-26 13:40
 */
public class JavaVMStackOOM {
    private int stackLength = 1;

    private void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        try {
            javaVMStackOOM.stackLeak();
        } catch (Throwable e) {
            System.out.println("stackLength=" + javaVMStackOOM.stackLength);
            throw e;
        }
    }
}
