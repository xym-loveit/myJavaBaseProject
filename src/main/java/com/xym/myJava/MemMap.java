package com.xym.myJava;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-29 15:43
 */
public class MemMap {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("d:/socketChannel.out", "rw");
            FileChannel channel = file.getChannel();
            //文件映射内存
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            long startTime = System.currentTimeMillis();
            byte[] b = new byte[1024];
            long len = file.length();
            for (int i = 0; i < file.length(); i += 1024*10) {
                if (len - i > 1024) {
                    buffer.get(b);
                } else {
                    buffer.get(new byte[(int) (len - i)]);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("使用内存映射方式读取文件总耗时： " + (endTime - startTime));


            //普通IO流方式
            long startTime1 = System.currentTimeMillis();
            while (channel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                byteBuffer.clear();
            }
            long endTime1 = System.currentTimeMillis();
            System.out.println("使用普通IO流方式读取文件总耗时： " + (endTime1 - startTime1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
