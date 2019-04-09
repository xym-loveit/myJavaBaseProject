package com.xym.myJava.netty_in_action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * 堆缓冲区（HeapByteBuf）比较适合----除网络通信的其他场景中使用
 * <p>
 * 直接缓冲区（DirectByteBuf）比较适合-----网络通信传输中使用
 *
 * @author xym
 * @create 2019-_01-18 13:52
 */
public class ByteBufTest {
    public static void main(String[] args) {
        //discardReadBytes();
        //test1();
        //sliceTest();

        ByteBuf byteBuf = Unpooled.copiedBuffer("02345678".getBytes());
        System.out.println(byteBuf.readBoolean());
        ByteBuf byteBuf2 = Unpooled.buffer();
        System.out.println(byteBuf2.maxCapacity());
    }

    private static void sliceTest() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("12345678".getBytes());
        ByteBuf slice = byteBuf.slice(0, 4);
        System.out.println(slice);
        //共享缓冲区，如果对切片进行了修改，源数据也一并会修改
        slice.setByte(0, '9');
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));

        //分段的副本变化不会引起源数据发生变化
        ByteBuf copy = byteBuf.copy(0, 4);
        copy.setByte(0, '1');
        System.out.println(copy.toString(CharsetUtil.UTF_8) + "---" + byteBuf.toString(CharsetUtil.UTF_8));

        //处于内存开销的考虑，一般情况下使用slice即可，但切记修改会影响源数据
    }

    private static void test1() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("12345678".getBytes());
        System.out.println(byteBuf);
        //是否还有可读数据
        while (byteBuf.isReadable()) {
            System.out.print((char) byteBuf.readByte());
        }
        //默认创建一个大小为256的堆缓冲区
        ByteBuf byteBuf2 = Unpooled.buffer();
        System.out.println(byteBuf2);
        Random random = new Random();
        while (byteBuf2.writableBytes() >= 4) {
            //随机写入4个长度的int数字ss
            byteBuf2.writeInt(random.nextInt(100));
        }

        //读取全部数据
        while (byteBuf2.isReadable()) {
            System.out.print(byteBuf2.readInt() + " ");
        }
        System.out.println("-------------------------");
        System.out.println(byteBuf2.indexOf(0, 100, Byte.valueOf("33")));
        //重置读索引、写索引至0位置，实际并不会清空缓冲区数据
        byteBuf2.clear();
    }

    /**
     * 缓冲区的实际回收情况
     */
    private static void discardReadBytes() {
        //compositeBuffer();
        ByteBuf byteBuf = Unpooled.copiedBuffer("123456789abcdefgh".getBytes());
        System.out.println(byteBuf);
        byte[] bytes = new byte[5];
        byteBuf.readBytes(bytes, 0, 4);
        System.out.println(byteBuf);
        /*回收已读取空间*/
        byteBuf.discardReadBytes();
        System.out.println(byteBuf);
        //将读索引和写索引重置到最大空间
        byteBuf.readerIndex(0);
        byteBuf.writerIndex(byteBuf.capacity());
        System.out.println(byteBuf);
        //获取所有可用数据，观察实际数据
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 复合缓冲区
     */
    private static void compositeBuffer() {
        //directByteBuf();
        //创建2个子缓冲区
        ByteBuf headBuf = Unpooled.buffer();
        ByteBuf bodyBuf = Unpooled.buffer();
        headBuf.writeBytes("12345678".getBytes());
        bodyBuf.writeBytes("abcdefgh".getBytes());
        //复合缓冲区
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        compositeByteBuf.addComponents(headBuf, bodyBuf);
        System.out.println(compositeByteBuf.toString());
        for (int i = 0; i < compositeByteBuf.numComponents(); i++) {
            System.out.println(compositeByteBuf.component(i));
        }
    }

    private static void directByteBuf() {
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes("123456".getBytes());
        System.out.println(byteBuf);
        //检查 ByteBuf 是否由数
        //组支撑。如果不是，则
        //这是一个直接缓冲区
        if (!byteBuf.hasArray()) {
            //获取可读
            //字节数
            int length = byteBuf.readableBytes();
            //分配一个新的数组来保存
            //具有该长度的字节数据
            byte[] bytes = new byte[length];
            //将字节复制到该数组
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            System.out.println(new String(bytes, 0, length));
        }
    }

    /**
     * HeapByteBuf(堆内缓冲区，内部通过数组操作,比较适合非网络传输的数据处理中)
     * <p>
     * 如果你的数据包含在一
     * 个在堆上分配的缓冲区中，那么事实上，在通过套接字发送它之前，JVM将会在内部把你的缓冲
     * 区复制到一个直接缓冲区中
     */
    private static void heapByteBuf() {

        ByteBuf byteBuf = Unpooled.copiedBuffer("abcd".getBytes());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.capacity());
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes("abcdef".getBytes());
        System.out.println(buffer.toString());
        System.out.println(buffer.readBytes(6).toString(CharsetUtil.UTF_8));
        System.out.println(buffer.toString());
        //System.out.println(buffer.readBoolean());
        System.out.println(byteBuf.arrayOffset());
        if (buffer.hasArray()) {
            //重置读索引至起始位置
            buffer.readerIndex(0);
            byte[] array = buffer.array();
            //计算第一个字节的偏移量
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            //计算第一个字节的偏移量
            int length = buffer.readableBytes();
            System.out.println(new String(array, offset, length));
        }
    }
}
