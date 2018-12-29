package com.xym.myJava.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO客户端处理器
 *
 * @author xym
 * @create 2018-12-29 16:15
 */
public class NioTimeClientThread implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel channel;
    private volatile boolean stop;

    public NioTimeClientThread(String host, int port) {
        this.host = (host == null ? "127.0.0.1" : host);
        this.port = port;
        try {
            //初始化多路复用器
            selector = Selector.open();
            //初始化客户端socket
            channel = SocketChannel.open();
            //设置非阻塞
            channel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        //多路复用器关闭后，所有注册在上面的channel和pipe等资源都会被自动去关闭
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel channel = (SocketChannel) key.channel();
            //判断是否连接成功
            if (key.isConnectable()) {
                if (channel.finishConnect()) {
                    channel.register(selector, SelectionKey.OP_READ);
                    doWrite(channel);
                } else {
                    //连接失败进程退出
                    System.exit(1);
                }
            }

            if (key.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("Now is :" + body);
                    this.stop = true;
                } else if (read < 0) {
                    //对端链路关闭
                    key.cancel();
                    channel.close();
                } else {
                    //读到0字节忽略
                }
            }
        }
    }


    private void doConnect() throws IOException {
        //如果直连连接成功，则注册到多路复用器上，发送请求消息，读应答
        if (channel.connect(new InetSocketAddress(host, port))) {
            channel.register(selector, SelectionKey.OP_READ);
            doWrite(channel);
        } else {
            //如果失败，再重新注册连接
            channel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    /**
     * 写消息
     *
     * @param channel
     * @throws IOException
     */
    private void doWrite(SocketChannel channel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(req.length);
        buffer.put(req);
        buffer.flip();
        channel.write(buffer);
        if (!buffer.hasRemaining()) {
            System.out.println("send order 2 server succeed.");
        }
    }
}
