package com.xym.myJava.netty.serialize.msgpack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 采用自定义分隔符DelimiterBasedFrameDecoder处理粘包和拆包问题
 *
 * @author xym
 * @create 2019-01-02 15:33
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
                    option(ChannelOption.SO_BACKLOG, 1024).
                    handler(new LoggingHandler(LogLevel.INFO)).childHandler(/*new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
                    ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                    ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                    ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                    ch.pipeline().addLast(new EchoServerHandler());
                   *//* ch.pipeline().addLast("msgpack decoder",new MsgpackDecoder());
                    ch.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));
                    ch.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
                    ch.pipeline().addLast(new EchoServerHandler());*//*
                }

            }*/new ChildChannelHandler());
            //绑定端口同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("服务器启动完成，等待客户端的连接和数据.....");
            //等待服务器监听端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程池资源
            try {
                workerGroup.shutdownGracefully().sync();
                bossGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("server receive client :[" + msg.toString() + "]");
            ctx.writeAndFlush(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
            ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
            ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
            ch.pipeline().addLast(new EchoServerHandler());
        }
    }

}
