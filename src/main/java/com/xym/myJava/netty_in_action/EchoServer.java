package com.xym.myJava.netty_in_action;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 口令回复服务端启动引导类
 *
 * @author xym
 * @create 2019-_01-18 9:54
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group).channel(NioServerSocketChannel.class).
                    localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //添加一个EchoServerHandler 到子Channel的 ChannelPipeline
                    ch.pipeline().addLast(new EchoServerHandler());
                }
            });
            //异步地绑定服务器；
            //调用 sync()方法阻塞
            //等待直到绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务端监听端口成功，等待客户端链接...");
            //获取 Channel CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            //关闭 EventLoopGroup，
            //释放所有的资源
            group.shutdownGracefully();
        }
    }
}
