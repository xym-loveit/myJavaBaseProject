package com.xym.myJava;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Comparator;

/**
 * lambda表达式可以赋值给函数式接口
 *
 * @author xym
 */
public class LambdaExp2 {
    public static void main(String[] args) {

        FileFilter fileFilter = (pathname) -> {
            return pathname.getName().endsWith(".txt");
        };

        FileFilter fileFilter2 = (pathname) -> pathname.getName().endsWith(".txt");

        FilenameFilter filenameFilter = (dir, name) -> name.endsWith(".txt");

        FilenameFilter filenameFilter2 = (File dir, String name) -> name.endsWith(".txt");

        FilenameFilter filenameFilter3 = (File dir, String name) -> {
            return name.endsWith(".txt");
        };

        Comparator<File> comparator = (f1, f2) -> {
            return f1.getName().compareTo(f2.getName());
        };

        Comparator<File> comparator2 = (f1, f2) -> f1.getName().compareTo(f2.getName());

        Runnable runnable = () -> {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        };

    }
}
