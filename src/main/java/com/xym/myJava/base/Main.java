package com.xym.myJava.base;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-07 23:04
 */
public class Main {

    final int anInt;

    public Main(int anInt) {
        this.anInt = anInt;
    }

    public void print() {
        int bnInt;
        System.out.println("anInt=" + anInt);
        //System.out.println("bnInt=" + bnInt);
    }

    public static void main(String[] args) {
        new Main(100).print();
    }
}
