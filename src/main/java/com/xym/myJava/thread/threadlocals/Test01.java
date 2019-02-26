package com.xym.myJava.thread.threadlocals;

/**
 * ThreadLocal(线程局部变量)
 * <p>
 * InheritableThreadLocal(可被子线程读取的线程局部变量--子线程和父线程共享)
 *
 * @author xym
 * @create 2019-02-25 10:59
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("在Main线程中取值=" + Tools.date.get());
            System.out.println("在Main线程中取值222=" + Tools.inheritableData.get());
            Thread.sleep(10);
        }

        Thread.sleep(5000);
        new ThreadA().start();
    }

    static public class Tools {
        public static ThreadLocalExt date = new ThreadLocalExt();

        //可被子线程获取到父线程ThreadLocal类型变量
        public static InheritableThreadLocalExt inheritableData = new InheritableThreadLocalExt();
    }

    static public class ThreadLocalExt extends ThreadLocal {

        @Override
        protected Object initialValue() {
            return System.currentTimeMillis();
        }
    }

    static public class InheritableThreadLocalExt extends InheritableThreadLocal {
        @Override
        protected Object initialValue() {
            return System.currentTimeMillis();
        }

        @Override
        protected Object childValue(Object parentValue) {
            return parentValue + "--我是在子线程中加的!!!";
        }
    }
}
