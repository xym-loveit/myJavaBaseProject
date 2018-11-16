package com.xym.myJava.nio;


import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 描述类作用
 * <p>
 * limit:表明还有多少数据需要取出(在从缓冲区写入通道时)，或者还有多少空间可以放入数据(在从通道读入缓冲区时)
 * position 变量跟踪已经写了多少数据,或指定下一个字节将放到数组的哪一个元素中
 * capacity :表明可以储存在缓冲区中的最大数据容量
 *
 * @author xym
 * @create 2018-11-15 14:46
 */
public class ByteBufferDemo {
    public static void main(String[] args) {

        byte[] bytes = new byte[]{12, 15, 56, 127, 50};
        byte[] bytes2 = new byte[5];
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        System.out.println("limit=" + wrap.limit() + "/position=" + wrap.position() + "/capacity=" + wrap.capacity());
        System.out.println(wrap.get());//每次读取一个字节
        System.out.println("limit=" + wrap.limit() + "/position=" + wrap.position() + "/capacity=" + wrap.capacity());
        System.out.println(wrap.get(bytes2, 0, 1) + "--" + Arrays.toString(bytes2));//每次读取指定个数组长度的字节
        System.out.println("limit=" + wrap.limit() + "/position=" + wrap.position() + "/capacity=" + wrap.capacity());
        System.out.println(wrap.hasRemaining());//是否还有内容未读取
        System.out.println(wrap.get(5 - 1));//读取指定索引位置上的字节，且不会引起状态变化（position、limit）
        // /事实上，它完全绕过了缓冲区的统计方法。
        System.out.println("limit=" + wrap.limit() + "/position=" + wrap.position() + "/capacity=" + wrap.capacity());
        //可以采用链式读法
        System.out.println(wrap.get(bytes2, 0, 1).get(bytes2, 3, 2));
        System.out.println(Arrays.toString(bytes2));
        //clear方法只是修改状态值并不会清空其数据
        wrap.clear();
        //缓冲区可以反复读
        while (wrap.hasRemaining()) {
            System.out.println("---" + wrap.get());
        }
        //因为读取完毕position已经和limit相等了，表示buffer缓冲区已放满，所以要想再存放数据，需要清理一下
        wrap.clear();
        wrap.put((byte) 22).put(Byte.valueOf("33")).put(new byte[]{44, 55, 66, 77}, 0, 3);
        System.out.println("limit=" + wrap.limit() + "/position=" + wrap.position() + "/capacity=" + wrap.capacity());
        System.out.println(Arrays.toString(wrap.array()));
        wrap.clear();
        //也可以做buffer的合并
        wrap.put(ByteBuffer.wrap(new byte[]{99, 88, 77, 66, 55}));
        System.out.println(Arrays.toString(wrap.array()));
        //修改指定位置，不会引起状态变化
        System.out.println(wrap.put(1, Byte.valueOf("24")));
        System.out.println(Arrays.toString(wrap.array()));
    }
}
