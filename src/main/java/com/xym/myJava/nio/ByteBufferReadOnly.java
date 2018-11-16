package com.xym.myJava.nio;

import java.nio.ByteBuffer;

/**
 * 防止被修改的只读缓冲区
 *
 * @author xym
 * @create 2018-11-15 16:53
 */
public class ByteBufferReadOnly {
    public static void main(String[] args) {
        //分配10个大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        System.out.println("/limit=" + allocate.limit() + "/position=" + allocate.position() + "/capacity=" + allocate.capacity());
        for (int i = 0; i < allocate.capacity(); i++) {
            //为缓冲区赋值
            allocate.put((byte) i);
        }
        System.out.println("/limit=" + allocate.limit() + "/position=" + allocate.position() + "/capacity=" + allocate.capacity());

        //创建只读缓冲区,实际上创建了个HeapByteBufferR对象，即新对象，只是数组（数据）公用
        ByteBuffer readOnlyBuffer = allocate.asReadOnlyBuffer();
        readOnlyBuffer.flip();
        //readOnlyBuffer.put(0, (byte) 100);
        System.out.println("r---/limit=" + readOnlyBuffer.limit() + "/position=" + readOnlyBuffer.position() + "/capacity=" + readOnlyBuffer.capacity());
        //while (readOnlyBuffer.hasRemaining()) {
        //    System.out.println(readOnlyBuffer.get());
        //}
        System.out.println("/limit=" + allocate.limit() + "/position=" + allocate.position() + "/capacity=" + allocate.capacity());
    }
}
