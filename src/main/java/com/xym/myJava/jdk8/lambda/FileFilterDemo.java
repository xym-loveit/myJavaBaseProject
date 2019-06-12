package com.xym.myJava.jdk8.lambda;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-11 11:00
 */
public class FileFilterDemo {
    public static int COUNT_DIR = 0;

    public static void main(String[] args) {
        //listDir(Paths.get("d://soft").toFile());
        //System.out.println(COUNT_DIR);
        //listDirWithLambda(Paths.get("d://soft").toFile());
        //listDirWithLambda2(Paths.get("d://soft").toFile());
        dirFiles(Paths.get("d://soft").toFile());
    }

    /**
     * 最原始
     *
     * @param file
     */
    public static void listDir(File file) {
        if (file.isDirectory()) {
            COUNT_DIR++;
            //System.out.println(file.getAbsolutePath());
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory();
                }
            });

            for (File file1 : files) {
                listDir(file1);
            }
        }
    }

    /**
     * 通过Lambda简化1
     *
     * @param file
     */
    public static void listDirWithLambda(File file) {
        if (file.isDirectory()) {
            Consumer out = System.out::println;
            out.accept(file.getAbsolutePath());
            File[] files = file.listFiles((path) -> path.isDirectory());
            Arrays.asList(files).forEach(FileFilterDemo::listDirWithLambda);
        }
    }

    /**
     * 通过Lambda简化2
     *
     * @param file
     */
    public static void listDirWithLambda2(File file) {
        if (file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            //方法引用
            File[] files = file.listFiles(File::isDirectory);
            //方法递归
            Stream.of(files).forEach(FileFilterDemo::listDirWithLambda2);
        }
    }

    public static void dirFiles(File file) {
        String[] list = file.list((d, name) -> {
            //System.out.println(d.isFile() + "--" + d.getAbsolutePath() + "--" + name);
            return name.endsWith("pdf");
        });
        Stream.of(list).forEach(System.out::println);
    }

    public static void dirFiles2(File file) {
        Stream.of(file.list((d, name) -> name.endsWith("pdf"))).forEach(System.out::println);
    }
}
