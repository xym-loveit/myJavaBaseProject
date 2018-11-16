package com.xym.myJava.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 聚集IO（将多个缓冲区数据合并为一个大的缓冲区）
 *
 * @author xym
 * @create 2018-11-16 10:45
 */
public class GatheringByteChannelDemo {
    public static void main(String[] args) throws IOException {
        ByteBuffer[] buffers = new ByteBuffer[]{
                ByteBuffer.wrap("java".getBytes()),
                ByteBuffer.wrap(" io".getBytes()),
                ByteBuffer.wrap(" thread多线程".getBytes())
        };

        for (ByteBuffer buffer : buffers) {
            System.out.println("/limit=" + buffer.limit() + "/position=" + buffer.position() + "/capacity=" + buffer.capacity());
        }

        FileChannel channel = new FileOutputStream("d:/gather.txt").getChannel();
        channel.write(buffers);
        channel.close();
    }
}
