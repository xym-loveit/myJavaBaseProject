package com.xym.myJava.netty.protocolStack.client;

import com.xym.myJava.netty.protocolStack.NettyConstant;
import com.xym.myJava.netty.protocolStack.codec.NettyMessageDecoder;
import com.xym.myJava.netty.protocolStack.codec.NettyMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Netty客户端
 *
 * @author xym
 * @create 2019-01-10 17:05
 */
public class NettyClient {

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) {
        Bootstrap bootstrap = new Bootstrap();
        //配置客户端NIO线程组
        bootstrap.group(group).channel(NioSocketChannel.class).
                option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyMessageDecoder((1024 * 1024), 4, 4));
                ch.pipeline().addLast("MessageEncoder", new NettyMessageEncoder());
                ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                ch.pipeline().addLast("loginAuthHandler", new LoginAuthReqHandler());
                ch.pipeline().addLast("heartBeatHandler", new HeartBeatReqHandler());
            }

        });
        //发起异步连接操作
        try {
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port), new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("start reconnecting");
            //所有资源释放完成后，清空资源，再次发起重连操作
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    //发起重连操作
                    connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


    }

    public static void main(String[] args) {
        new NettyClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
    }
}
