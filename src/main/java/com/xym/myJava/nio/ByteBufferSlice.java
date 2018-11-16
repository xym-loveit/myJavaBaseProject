package com.xym.myJava.nio;

import java.nio.ByteBuffer;

/**
 * 缓冲区分片后可视为源缓冲区的一个“窗口”
 *
 * @author xym
 * @create 2018-11-15 16:19
 */
public class ByteBufferSlice {
    public static void main(String[] args) {
        //slice1();
        slice2();
    }

    private static void slice2() {
        //分配10个大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        System.out.println("/limit=" + allocate.limit() + "/position=" + allocate.position() + "/capacity=" + allocate.capacity());
        for (int i = 0; i < allocate.capacity(); i++) {
            //为缓冲区赋值
            allocate.put((byte) i);
        }

        //当前需要对缓冲区的一个分片进行数据的更改，则只需提取出来
        //传递给处理函数即可
        allocate.position(2);
        allocate.limit(5);
        ByteBuffer slice = allocate.slice();
        sliceHandler(slice);
        //allocate.clear();
        allocate.position(0);
        allocate.limit(allocate.capacity());
        System.out.println("---" + allocate.remaining());
        while (allocate.remaining() > 0) {
            System.out.println(allocate.get());
        }
    }

    /**
     * 使用该函数对部分数据（窗口数据）进行处理，
     *
     * @param slice
     */
    private static void sliceHandler(ByteBuffer slice) {
        //System.out.println(slice);
        while (slice.hasRemaining()) {
            byte b = slice.get();
            slice.put(slice.position() - 1, (byte) (b * 11));
        }
    }

    private static void slice1() {
        //分配10个大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        System.out.println("/limit=" + allocate.limit() + "/position=" + allocate.position() + "/capacity=" + allocate.capacity());
        for (int i = 0; i < allocate.capacity(); i++) {
            //为缓冲区赋值
            allocate.put((byte) i);
        }
        //为赋值缓冲区做准备
        allocate.clear();
        allocate.limit(7);
        allocate.position(3);
        ByteBuffer slice = allocate.slice();
        System.out.println("/limit=" + slice.limit() + "/position=" + slice.position() + "/capacity=" + slice.capacity());
        while (slice.hasRemaining()) {
            System.out.println(slice.get());
            if (slice.position() == 3) {
                //更改赋值缓冲区后，源缓冲区数据也一起更改了，说明源缓冲区和赋值缓冲区底层公用同一个数组
                slice.put((byte) 55);
            }
        }
        System.out.println("/limit=" + slice.limit() + "/position=" + slice.position() + "/capacity=" + slice.capacity());
        //遍历的同时发现源缓冲区的状态值并未被改变
        System.out.println("/limit=" + allocate.limit() + "/position=" + allocate.position() + "/capacity=" + allocate.capacity());
        while (allocate.hasRemaining()) {
            System.out.println(allocate.get());
        }
    }
}
