package com.xym.myJava.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-16 16:_01
 */
public class BufferReadFileDemo {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:/student.sql", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        //buffer.compact();
        //buffer.rewind();重复读取position=0,limit不变
        //读取数据到缓冲区
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            System.out.println("\nRead " + bytesRead);
            buffer.flip();//倒转，将读转为写
            //消费缓冲区中数据
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            //清空缓冲区，为下次读取做准备
            buffer.clear();
            bytesRead = channel.read(buffer);
        }
        //关闭文件操作
        randomAccessFile.close();
    }
}
