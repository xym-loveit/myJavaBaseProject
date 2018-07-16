package com.xym.myJava;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * @author xym
 */
public class LambdaExp {
    public static void main(String[] args) {
        File file = new File(".");
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });

        for (File file1 : files) {
            System.out.println(file1.getName());
        }

        /**
         * 最原始
         */
        file.listFiles((File dir, String name) -> {
            if (name.endsWith(".txt")) {
                return true;
            } else {
                return false;
            }
        });

        /**
         * 变形后的第二种
         */
        file.listFiles((File dir, String name) -> {
            return name.endsWith(".txt");
        });

        /**
         * 只有一句可以去掉大括号和return
         */
        file.listFiles((File dir, String name) -> name.endsWith(".txt"));

        /**
         *取消参数类型，变的最简洁
         */
        File[] files1 = file.listFiles((dir, name) -> name.endsWith(".txt"));

        /**
         * 排序的lambda
         */
        Arrays.sort(files1, (f1, f2) -> f1.getName().compareTo(f2.getName()));

        for (File file1 : files1) {
            System.out.println(file1.getName());
        }

        /***
         * 线程使用lambda
         */

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().toString());
        });
        thread.start();

        String msg = "abc";
        msg = "def";
        /**
         * 和匿名内部类一样只能访问final类型
         */
        String finalMsg = msg;
        Thread thread2 = new Thread(() -> {
            System.out.println(finalMsg + "--" + Thread.currentThread().toString());
        });
        thread2.start();
    }
}
