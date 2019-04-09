package com.xym.myJava.netty.decoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 采用自定义分隔符DelimiterBasedFrameDecoder处理粘包和拆包问题
 *
 * @author xym
 * @create 2019-_01-02 15:33
 */
public class EchoServer {
    public static void main(String[] args) {
        int port = 8080;
        try {
            if (args != null && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }
        } catch (NumberFormatException e) {
            port = 8080;
        }

        new EchoServer().bind(port);
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
                    option(ChannelOption.SO_BACKLOG, 100).
                    handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
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

        private int counter;

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            //发生异常，关闭链路
            ctx.close();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String body = (String) msg;
            System.out.println("this is " + (++counter) + " times receive client :[" + body + "]");
            //发送消息前重新组装分隔符
            body = body + "$_";
            ByteBuf byteBuf = Unpooled.copiedBuffer(body.getBytes());
            ctx.writeAndFlush(byteBuf);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }
}
