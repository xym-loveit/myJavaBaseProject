package com.xym.myJava.guava;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-04 10:12
 */
public class FilesDemo {
    public static void main(String[] args) {
        //copy();
        //move();
        //readline();
        //allRead();
        //fileHash();
        //writeFile();
        //touch();
        //traversal();
    }

    private static void traversal() {
        //广度优先
        //FluentIterable<File> files = Files.fileTreeTraverser().breadthFirstTraversal(new File("d://workspace"));
        //files.forEach(System.out::println);
        String path = "d://workspace";
        //深度优先
        Files.fileTreeTraverser().preOrderTraversal(new File(path)).forEach(System.out::println);
    }

    private static void touch() {
        try {
            //创建空文件
            Files.touch(new File("1234"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile() {
        try {
            //Files.asCharSink(new File("d://雪豹突击队.txt"), Charsets.UTF_8).write("写File咯，1234");
            Files.asCharSink(new File("d://雪豹突击队.txt"), Charsets.UTF_8, FileWriteMode.APPEND).write("\n\n追加写File咯，1234");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileHash() {
        try {
            //在文件对比时候，可以看两个文件等hashCode 是否相等，比如拷贝文件后是否有损
            HashCode hash = Files.asByteSource(new File("d://雪豹突击队.txt")).hash(Hashing.sha256());
            System.out.println(hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void allRead() {
        try {
            //文件整体读取
            String oneLine = Files.asCharSource(new File("d://雪豹突击队.txt"), Charsets.UTF_8).read();
            System.out.println(oneLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readline() {
        try {
            //readline
            List<String> strings = Files.readLines(new File("d://雪豹突击队.txt"), Charsets.UTF_8);
            System.out.println("内容--" + Joiner.on("\n").join(strings));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void move() {
        try {
            //剪切
            Files.move(new File("D:\\迅雷下载\\calibre-3.44.0.msi"), new File("d://calibre.msi"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copy() {
        try {
            Files.copy(new File("D:\\迅雷下载\\calibre-3.44.0.msi"), new File("d://calibre.msi"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
