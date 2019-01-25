package com.xym.myJava.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-01-16 10:56
 */
public class ByteBufferMain {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap("12345".getBytes());
        System.out.println(buffer);
        ByteBuf buffer1 = Unpooled.buffer();
        System.out.println(buffer1);
        buffer1.writeBytes("abcdef".getBytes());
        for (int i = 0; i < buffer1.capacity(); i++) {
            System.out.print((char)buffer1.getByte(i));
        }
        System.out.println(buffer1);
    }
}
