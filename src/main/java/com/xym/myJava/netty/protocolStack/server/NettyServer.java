package com.xym.myJava.netty.protocolStack.server;

import com.xym.myJava.netty.protocolStack.NettyConstant;
import com.xym.myJava.netty.protocolStack.codec.NettyMessageDecoder;
import com.xym.myJava.netty.protocolStack.codec.NettyMessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.IOException;

public class NettyServer {

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                //父类AbstractBootstrap中的handler是个工厂类，它为每个新接入的客户端都创建一个新的Handler
                .handler(new LoggingHandler(LogLevel.INFO))
                //ServerBootstrap中的handler是NioServerSocketChannel使用，所有连接该监听端口的客户端都会执行它
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws IOException {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                        ch.pipeline().addLast("loginAuthHandler", new LoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                    }
                });

        // 绑定端口，同步等待成功
        ChannelFuture future = b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
        System.out.println("Netty server start ok : " + (NettyConstant.REMOTEIP + " : " + NettyConstant.PORT));
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}