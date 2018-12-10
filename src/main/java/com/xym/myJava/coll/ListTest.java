package com.xym.myJava.coll;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * list的性能比较
 *
 * @author xym
 * @create 2018-12-10 14:58
 */
public class ListTest {
    //迭代次数
    public static int ITERATION_NUM = 100000;

    public static void main(String[] args) {
        insertPerformanceCompare();
        //getPerformanceCompare();
    }

    //新增性能比较：
    public static void insertPerformanceCompare() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("LinkedList新增测试开始");
        long start = System.nanoTime();
        List<Integer> linkedList = new LinkedList<Integer>();
        for (int x = 0; x < ITERATION_NUM; x++) {
            ((LinkedList<Integer>) linkedList).addFirst(x);
        }
        long end = System.nanoTime();
        System.out.println(end - start);

        System.out.println("ArrayList新增测试开始");
        start = System.nanoTime();
        List<Integer> arrayList = new ArrayList<Integer>();
        for (int x = 0; x < ITERATION_NUM; x++) {
            arrayList.add(x);
        }
        end = System.nanoTime();
        System.out.println(end - start);
    }

    //获取性能比较：
    public static void getPerformanceCompare() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //填充ArrayList集合：
        List<Integer> arrayList = new ArrayList<Integer>();
        for (int x = 0; x < ITERATION_NUM; x++) {
            arrayList.add(x);
        }

        //填充LinkedList集合：
        List<Integer> linkedList = new LinkedList<Integer>();
        for (int x = 0; x < ITERATION_NUM; x++) {
            linkedList.add(x);
        }

        //创建随机数对象：
        Random random = new Random();

        System.out.println("LinkedList获取测试开始");
        long start = System.nanoTime();
        for (int x = 0; x < ITERATION_NUM; x++) {
            int j = random.nextInt(x + 1);
            int k = linkedList.get(j);
        }
        long end = System.nanoTime();
        System.out.println(end - start);

        System.out.println("ArrayList获取测试开始");
        start = System.nanoTime();
        for (int x = 0; x < ITERATION_NUM; x++) {
            int j = random.nextInt(x + 1);
            int k = arrayList.get(j);
        }
        end = System.nanoTime();
        System.out.println(end - start);
    }

}
