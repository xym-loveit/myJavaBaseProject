package com.xym.myJava;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁
 *
 * @author xym
 * @create 2018-11-29 17:48
 */
public class NIOLock {
    public static void main(String[] args) {
        FileLock lock1 = null;
        try {
            FileChannel fileChannel = new RandomAccessFile("d:/test.txt", "rw").getChannel();
            // 写入4个字节
            fileChannel.write(ByteBuffer.wrap("abcd".getBytes()));
            // 将前2个字节区域锁定（共享锁）
            lock1 = fileChannel.lock(0, 2, true);
            // 当前锁持有锁的类型（共享锁/独占锁）
            System.out.println(lock1.isShared());
            fileChannel.write(ByteBuffer.wrap("a".getBytes()));
            // 可以修改共享锁之外的区域，从第三个字节开始写入
            fileChannel.write(ByteBuffer.wrap("ef".getBytes()), 2);
            //// OverlappingFileLockException 重叠的文件锁异常
            //FileLock lock2 = fileChannel.lock(0, 3, true);
            System.out.println("得到创建锁的通道--" + lock1.channel());
            long position = lock1.position();
            System.out.println("锁的起始位置==" + position);
            long size = lock1.size();
            System.out.println("锁的范围==" + size);
            System.out.println("判断锁是否与指定文件区域有重叠==" + lock1.overlaps(size - 1, fileChannel.size()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lock1.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
