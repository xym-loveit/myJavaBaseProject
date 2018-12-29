package com.xym.myJava.io.aio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-29 23:16
 */
public class AsyncTimeServerHandler implements Runnable {

    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel socketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            socketChannel = AsynchronousServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doAccept() {
        //AcceptCompletionHandler为消息接受成功后的回调
        socketChannel.accept(this, new AcceptCompletionHandler());
    }
}
