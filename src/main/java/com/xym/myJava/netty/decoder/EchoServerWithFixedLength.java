package com.xym.myJava.netty.decoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 固定长度解码器
 *
 * @author xym
 * @create 2019-01-02 16:15
 */
public class EchoServerWithFixedLength {
    public static void main(String[] args) {
        int port = 8080;
        try {
            if (args != null && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }
        } catch (NumberFormatException e) {
            port = 8080;
        }

        new EchoServerWithFixedLength().bind(port);
    }

    public void bind(int port) {
        //配置服务端的NIO线程组

        //用于接口客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用于处理网络io事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 1024).
                    handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    //ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                    //注意这里的FixedLengthFrameDecoder的参数
                    ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new EchoServerHandler());
                }

            });
            //绑定端口同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待服务器监听端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程池资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private class EchoServerHandler extends ChannelHandlerAdapter {

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            //发生异常，关闭链路
            ctx.close();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            String body = (String) msg;
            System.out.println("the receive client :[" + body + "]");
            //ctx.writeAndFlush(Unpooled.copiedBuffer("HTTP/1.1 200 OK\n Content-Type: text/html; charset=UTF-8\n\n http test".getBytes()));
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            System.out.println("--------------------channelReadComplete");
            ctx.flush();
        }
    }
}
