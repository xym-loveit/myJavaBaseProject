package com.xym.myJava.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将文件映射到内存
 *
 * @author xym
 * @create 2018-11-15 17:14
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:/thread.log", "rw");
        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 24);
        System.out.println("/limit=" + map.limit() + "/position=" + map.position() + "/capacity=" + map.capacity());
        //while (map.hasRemaining()) {
        //    System.out.println(map.get());
        //}
        map.put(0, (byte) 66);
        map.putInt(12);
        randomAccessFile.close();
    }
}
