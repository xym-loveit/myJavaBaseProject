package com.xym.myJava.aha;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序并去除重复-----------------------采用桶排序法和冒泡排序法
 *
 * @author xym
 * @create 2019-04-14 17:22
 */
public class SortAndRemoveDup {
    public static final Random RANDOM = new Random();
    public static final int MAX = 100;

    public static void main(String[] args) {
        int[] ints = new int[100];
        for (int i = 0; i < ints.length; i++) {
            //ints[i] = RANDOM.nextInt(1000);
            ints[i] = RANDOM.nextInt(MAX);
        }
        useBucket(MAX, ints);
        useBubbleSort(ints);
    }

    /**
     * 采用桶排序去重(适合数字有一定的范围，且数据量较小比如：0-10000)
     *
     * @param max 数字允许的最大值
     * @param a   源数组
     */
    private static void useBucket(int max, int[] a) {
        //注意当前数组的下标是已经有序的,且注意为max+1个
        int[] arr = new int[max + 1];
        //数组所有元素初始化值为0，表示一个都未出现过
        Arrays.fill(arr, 0);
        for (int i = 0; i < a.length; i++) {
            //出现过则标记为1
            arr[a[i]] = 1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                System.out.print(" " + i);
            }
        }
    }

    /**
     * 采用冒泡排序
     *
     * @param arr
     */
    private static void useBubbleSort(int[] arr) {
        int t = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        System.out.println("size=" + arr.length + Arrays.toString(arr));
        StringBuilder stringBuffer = new StringBuilder("[");
        /**
         * 排序好的元素和之间进行比较，只输出不重复的,注意i 从1开始
         */
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                stringBuffer.append(",").append(arr[i - 1]);
                count++;
            }
            //因为从1开始，而append(arr[i - 1]);所以要把最后一位算上
            if (i == arr.length - 1) {
                stringBuffer.append(",").append(arr[i]);
            }

        }
        System.out.println("size=" + count + stringBuffer.deleteCharAt(1).append("]").toString());
    }
}
