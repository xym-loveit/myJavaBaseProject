package com.xym.myJava.oldio;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-14 15:50
 */
public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {
        //randomAccessFileRead();
        randomAccessFileWrite();
    }

    private static void randomAccessFileRead() throws IOException {
        // 创建一个RandomAccessFile对象
        RandomAccessFile file = new RandomAccessFile("d:/test.txt", "rw");
        // 通过seek方法来移动读写位置的指针
        file.seek(10);
        // 获取当前指针
        long pointerBegin = file.getFilePointer();
        // 从当前指针开始读
        byte[] contents = new byte[1024];
        file.read(contents);
        long pointerEnd = file.getFilePointer();
        System.out.println("pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" + new String(contents));
        file.close();
    }

    public static void randomAccessFileWrite() throws IOException {
        // 创建一个RandomAccessFile对象
        RandomAccessFile file = new RandomAccessFile("d:/test.txt", "rw");
        // 通过seek方法来移动读写位置的指针
        file.seek(10);
        // 获取当前指针
        long pointerBegin = file.getFilePointer();
        // 从当前指针位置开始写
        file.write("HELLO WORD".getBytes());
        long pointerEnd = file.getFilePointer();
        System.out.println("pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n");
        file.close();
    }

}
