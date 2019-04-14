package com.xym.myJava.aha;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序-----------------比较开始选择一个基准点，一般选择数组的第一个元素
 * 数组左边的比基准元素小，右边的比基准元素大，反之则交换元素，交换之后继续下一轮比较
 * 通过一轮（二分法全部比较）的比较之后改变基准点递归比较
 *
 * @author xym
 * @create 2019-04-14 16:33
 */
public class QuickSortDemo {


    public static final int COUNT = 100_0000;
    public static final Random RANDOM = new Random(COUNT);

    public static void main(String[] args) {
        //int[] ints = getInputStr();
        //quicksort(ints, 0, ints.length - 1);
        //System.out.println(Arrays.toString(ints));
        int[] ints = new int[COUNT];
        for (int i = 0; i < COUNT; i++) {
            ints[i] = RANDOM.nextInt(COUNT);
        }
        long timeMillis = System.currentTimeMillis();
        quicksort(ints, 0, ints.length - 1);
        System.out.println("quick cost " + (System.currentTimeMillis() - timeMillis) + " ms");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("d:/sort.txt");
            writer.print(Arrays.toString(ints));
            writer.flush();
            //writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * @param left  数组索引--探测哨兵
     * @param right 数组索引--探测哨兵
     */
    private static void quicksort(int[] a, int left, int right) {
        //base为基准数,参考点
        int base = 0;
        int i = 0;
        int j = 0;
        int temp = 0;
        if (left > right) {
            return;
        }

        //开始依最左边第一个数字为基准
        base = a[left];
        i = left;
        j = right;

        while (i != j) {
            //顺序很重要，先从右往左找,右边的数都要比左边的数大
            while (a[j] >= base && i < j) {
                j--;
            }
            //在从左往右找，左边的数都要比基准数小
            while (a[i] <= base && i < j) {
                i++;
            }
            //当2个哨兵没有相遇,交换2个数在数组中的位置,即调整哨兵在检测的过程中的不符的情况
            if (i < j) {
                temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
        }

        //相遇了,说明此刻基数要归位
        a[left] = a[i];
        a[i] = base;

        quicksort(a, left, i - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(a, i + 1, right);//继续处理右边的，这里是一个递归的过程
    }

    private static int[] getInputStr() {
        System.out.println("输入0-10的数字：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = null;
            while ((line = reader.readLine()) == null || line.trim().equals("")) {
                System.out.println("请重新输入：");
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            String[] strings = line.split(" ");
            int[] array = new int[strings.length];
            int i = 0;
            for (String string : strings) {
                array[i++] = Integer.valueOf(string);
            }
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
