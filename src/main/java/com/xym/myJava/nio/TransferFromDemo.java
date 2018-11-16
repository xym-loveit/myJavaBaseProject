package com.xym.myJava.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * TransferFrom:transferFrom()方法可以将数据从源通道传输到FileChannel中
 *
 * @author xym
 * @create 2018-11-16 17:30
 */
public class TransferFromDemo {
    public static void main(String[] args) throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("d:/student.sql", "rw");
        RandomAccessFile outFile = new RandomAccessFile("d:/student_out.sql", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        long count = fromChannel.size();
        FileChannel outFileChannel = outFile.getChannel();
        //transferFrom,将源通道（fromChannel）数据传输到目标通道（outFileChannel），数据传输大小为count-position
        long len = outFileChannel.transferFrom(fromChannel, 0, count);
        System.out.println(len);

        RandomAccessFile fromFile2 = new RandomAccessFile("d:/student.sql", "rw");
        RandomAccessFile outFile2 = new RandomAccessFile("d:/student_out2.sql", "rw");
        FileChannel fromFile2Channel = fromFile2.getChannel();
        FileChannel outFile2Channel = outFile2.getChannel();
        //和上面方法差不多，只是调换了下顺序（调用源通道方法，参数为目标通道）
        long len2 = fromFile2Channel.transferTo(0, fromFile2Channel.size(), outFile2Channel);
        System.out.println(len2);
    }
}
