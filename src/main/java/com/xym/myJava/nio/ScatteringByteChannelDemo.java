package com.xym.myJava.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散IO/分散通道
 *
 * @author xym
 * @create 2018-11-16 10:26
 */
public class ScatteringByteChannelDemo {
    public static void main(String[] args) throws IOException {
        ByteBuffer[] buffers = new ByteBuffer[]{
                ByteBuffer.allocate(1024),//每个缓冲区存1k
                ByteBuffer.allocate(1024),//每个缓冲区存1k
                ByteBuffer.allocate(1024)//每个缓冲区存1k
        };
        FileChannel channel = new FileInputStream("d:/student.sql").getChannel();
        channel.read(buffers);
        for (int i = 0; i < buffers.length; i++) {
            System.out.println("第 " + i + "个缓冲区...");
            ByteBuffer buffer = buffers[i];
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            System.out.println("\n");
        }
    }
}
