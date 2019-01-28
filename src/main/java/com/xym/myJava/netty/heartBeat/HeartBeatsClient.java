package com.xym.myJava.netty.heartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-01-28 15:20
 */
public class HeartBeatsClient {
    public static final int PORT = 8080;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO)).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    /**
                     * 注意这里设置的是4秒写超时,因为服务器是5秒检测读超时
                     */
                    p.addLast("ping", new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                    p.addLast("decoder", new StringDecoder());
                    p.addLast("encoder", new StringEncoder());
                    p.addLast(new HeartBeatClientHandler());
                }
            });

            ChannelFuture sync = b.connect(HOST, PORT).sync();
            System.out.println("客户端已链接上，准备发送消息...");
            sync.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
