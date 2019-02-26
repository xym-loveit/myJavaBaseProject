package com.xym.myJava.thread.waitNotify;

/**
 * 调用signalAll进行多条件唤醒
 *
 * @author xym
 * @create 2019-02-25 13:41
 */
public class UseMoreConditionWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        MyserviceMoreCondition service = new MyserviceMoreCondition();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        Thread.sleep(3000);

        //只唤醒了A，B还在wait中
        service.signalAll_A();
    }

    static public class ThreadA extends Thread {
        private MyserviceMoreCondition moreCondition;

        public ThreadA(MyserviceMoreCondition moreCondition) {
            this.moreCondition = moreCondition;
        }

        @Override
        public void run() {
            moreCondition.awaitA();
        }
    }

    static public class ThreadB extends Thread {

        private MyserviceMoreCondition moreCondition;

        public ThreadB(MyserviceMoreCondition moreCondition) {
            this.moreCondition = moreCondition;
        }

        @Override
        public void run() {
            moreCondition.awaitB();
        }
    }
}
