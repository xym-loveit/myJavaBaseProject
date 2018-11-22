package com.xym.myJava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-19 16:21
 */
public class WebServerDemo {
    public static void main(String[] args) throws IOException {

        //1.通过ServerSocketChannel 的open()方法创建一个ServerSocketChannel对象，open方法的作用：打开套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //2.通过ServerSocketChannel绑定ip地址和port(端口号)
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
        //通过ServerSocketChannelImpl的accept()方法创建一个SocketChannel对象用于从客户端读/写数据
        SocketChannel accept = ssc.accept();
        //3.创建写数据的缓存区对象
        ByteBuffer allocate = ByteBuffer.allocate(128);
        //4.创建读数据的缓存区对象
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        allocate.put("hello Webclient this is from Webserver!".getBytes());
        allocate.flip();
        accept.write(allocate);
        accept.read(readBuffer);
        StringBuilder stringBuilder = new StringBuilder();
        readBuffer.flip();
        while (readBuffer.hasRemaining()) {
            stringBuilder.append((char) readBuffer.get());
        }
        System.out.println("从客户端接受到的数据:" + stringBuilder);
        accept.close();
        ssc.close();
    }
}
