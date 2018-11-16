package com.xym.myJava.nio;

import java.nio.ByteBuffer;

/**
 * 由于ByteBuffer实现了Comparable接口，所以还具备比较的特性
 *
 * @author xym
 * @create 2018-11-16 17:06
 */
public class BufferEqualsDemo {
    public static void main(String[] args) {
        ByteBuffer wrap = ByteBuffer.wrap("adcdef".getBytes());
        ByteBuffer wrap1 = ByteBuffer.wrap("123def".getBytes());
        System.out.println(wrap + "--" + wrap1);
        System.out.println(wrap.equals(wrap1));
        wrap.get(new byte[3]);
        wrap1.get(new byte[3]);
        //wrap.flip();
        //返回为true，说明equals方法，仅仅是比较剩余的内容（未读取的内容，即position与limit之间的内容）
        System.out.println(wrap.equals(wrap1));
    }
}
