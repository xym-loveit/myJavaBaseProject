package com.xym.myJava.aha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 冒泡排序----两两比较，特别浪费时间复杂度到了N（数字个数）的平方
 * <p>
 * 相邻元素两两比较，符合规则，则交换顺序
 *
 * @author xym
 * @create 2019-04-14 15:07
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
        //System.out.println(getInputStr());
        String str = getInputStr();
        if (str != null) {
            String[] nums = str.split(" ");
            String temp = null;
            //只用比较n-1趟，n表示为要比较的数字长度
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = 0; j < nums.length - 1 - i; j++) {
                    if (Integer.valueOf(nums[j]).intValue() > Integer.valueOf(nums[j + 1])) {
                        temp = nums[j];
                        nums[j] = nums[j + 1];
                        nums[j + 1] = temp;
                    }
                }
            }

            System.out.println(Arrays.toString(nums));
        }
    }

    private static String getInputStr() {
        System.out.println("输入0-10的数字：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = null;
            while ((line = reader.readLine()) == null || line.trim().equals("")) {
                System.out.println("请重新输入：");
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
