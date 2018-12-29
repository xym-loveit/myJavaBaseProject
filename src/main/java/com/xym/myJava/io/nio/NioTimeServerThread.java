package com.xym.myJava.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio处理器
 *
 * @author xym
 * @create 2018-12-29 15:22
 */
public class NioTimeServerThread implements Runnable {

    private Selector selector;
    private ServerSocketChannel socketChannel;
    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     *
     * @param port
     */
    public NioTimeServerThread(int port) {
        try {
            //多路复用器
            selector = Selector.open();
            //服务端socket通道
            socketChannel = ServerSocketChannel.open();
            //开启非阻塞
            socketChannel.configureBlocking(false);
            //绑定socket
            socketChannel.socket().bind(new InetSocketAddress(port), 1024);
            //绑定多路复用器,并注册接受事件消息
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("the time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                //休眠时间为1秒
                selector.select(1000);
                //得到注册事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    //处理过的需要手动移除
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            //断开链路
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //多路复用器关闭后，所有注册在上面的channel和pipe等资源都会自动关闭，
        // 所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 请求处理逻辑
     *
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //处理新接入的请求消息
            if (key.isAcceptable()) {
                //如果是第一次链接请求则走
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel accept = channel.accept();
                accept.configureBlocking(false);
                accept.register(selector, SelectionKey.OP_READ);
            }
            //如果是读请求则走
            if (key.isReadable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                //从通道读取数据到缓冲区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(readBuffer);
                if (read > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("the time server receive oder :" + body + ",from " + channel.socket());
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(channel, currentTime);
                } else if (read < 0) {
                    //断开链路
                    key.cancel();
                    channel.close();
                } else {
                    //读取到0字节，忽略
                }
            }
        }
    }

    /**
     * 向客户端通道写入信息
     *
     * @param channel
     * @param resp
     * @throws IOException
     */
    private void doWrite(SocketChannel channel, String resp) throws IOException {
        if (resp != null && resp.trim().length() > 0) {
            byte[] bytes = resp.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
        }
    }
}
