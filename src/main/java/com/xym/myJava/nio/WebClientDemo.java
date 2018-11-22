package com.xym.myJava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-19 16:33
 */
public class WebClientDemo {
    public static void main(String[] args) throws IOException {
        //1.通过SocketChannel的open()方法创建一个SocketChannel对象
        SocketChannel open = SocketChannel.open();
        ///2.连接到远程服务器（连接此通道的socket）
        open.connect(new InetSocketAddress("127.0.0.1", 8888));
        // 3.创建写数据缓存区对象
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("hello WebServer this is from WebClient".getBytes());
        writeBuffer.flip();
        open.write(writeBuffer);


        //创建读数据缓存区对象
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        open.read(readBuffer);
        StringBuilder stringBuilder = new StringBuilder();
        readBuffer.flip();
        while (readBuffer.hasRemaining()) {
            stringBuilder.append(((char) readBuffer.get()));
        }
        System.out.println("从服务端接受到的数据:" + stringBuilder);
        open.close();
    }
}
