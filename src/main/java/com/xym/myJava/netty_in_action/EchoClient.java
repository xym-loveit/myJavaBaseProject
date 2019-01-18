package com.xym.myJava.netty_in_action;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 口令回复客户端
 *
 * @author xym
 * @create 2019-01-18 10:19
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).
                    remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new EchoClientHandler());
                }
            });
            //连接到远程节点，阻塞等待直到连接完成
            ChannelFuture sync = bootstrap.connect().sync();
            System.out.println("客户端链接成功，准备发送消息给服务端...");
            //阻塞，直到Channel 关闭
            sync.channel().closeFuture().sync();
        } finally {
            //关闭线程池并且，释放所有的资源
            group.shutdownGracefully();
        }
    }
}
