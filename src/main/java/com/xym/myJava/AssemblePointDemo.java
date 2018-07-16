package com.xym.myJava;

public class AssemblePointDemo {

    static class Tourist extends Thread {
        AssemblePoint assemblePoint;

        public Tourist(AssemblePoint assemblePoint) {
            this.assemblePoint = assemblePoint;
        }

        @Override
        public void run() {
            try {
                //模拟各自独立运行
                Thread.sleep((int) (Math.random() * 1000));
                assemblePoint.await();//集合
                System.out.println("--arrived--");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Tourist[] tourists = new Tourist[10];
        AssemblePoint assemblePoint = new AssemblePoint(10);
        for (int i = 0; i < 10; i++) {
            tourists[i] = new Tourist(assemblePoint);
            tourists[i].start();
        }
    }
}
