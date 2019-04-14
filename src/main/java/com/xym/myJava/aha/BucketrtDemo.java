package com.xym.myJava.aha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 桶排序----------------------------浪费空间，假设数字范围是1------------10000000则数组就非常大，超级占用空间
 *
 * @author xym
 * @create 2019-04-14 14:27
 */
public class BucketrtDemo {
    public static void main(String[] args) {

        //初始化桶，标记桶里面所有数字暂且未出来（所有索引处值为0,表示该数字出现0次）
        int[] buckets = new int[11];
        for (int index : buckets) {
            buckets[index] = 0;
        }
        System.out.println("输入0-10的数字：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = null;
            while ((line = reader.readLine()) == null || line.trim().equals("")) {
                System.out.println("请重新输入：");
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            String[] nums = line.split(" ");
            for (String num : nums) {
                //数字如果出现则计数+1
                buckets[Integer.valueOf(num)]++;
            }

            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] > 0) {
                    //出现几次打印几次,只关心出现过的 数字
                    for (int j = 0; j < buckets[i]; j++) {
                        System.out.print(" " + i);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
