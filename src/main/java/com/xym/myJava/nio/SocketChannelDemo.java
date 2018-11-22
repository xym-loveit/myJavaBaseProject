package com.xym.myJava.nio;

import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-19 14:17
 */
public class SocketChannelDemo {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
            // 3.创建写数据缓存区对象
            ByteBuffer writeBuffer =ByteBuffer.allocate(128);
            writeBuffer.put("hello WebServer this is from WebClient".getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            FileChannel channel = new FileOutputStream("d:/socketChannel.out").getChannel();
            buffer.clear();
            int read = socketChannel.read(buffer);
            while (read != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                read = socketChannel.read(buffer);
            }
            channel.close();
            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
