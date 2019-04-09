package com.xym.myJava.netty.serialize.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * protobuf版本图书订购服务端
 *
 * @author xym
 * @create 2019-_01-03 17:24
 */
public class SubReqServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }

        new SubReqServer().bind(port);
    }

    public void bind(int port) {
        //用于接受客户端线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用于处理网络io事件线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //处理半包问题
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            //告诉ProtobufDecoder需要解码的目标类
                            ch.pipeline().addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast(new SubReqServerHandler());
                        }
                    });

            //绑定端口，同步等待成功
            ChannelFuture sync = bootstrap.bind(port).sync();
            System.out.println("服务器启动完成，等待客户端的连接和数据.....");
            //等待服务器，监听端口关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class SubReqServerHandler extends ChannelHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            //由于ProtobufDecoder已经对消息进行了自动解码，因此受到的消息可以直接使用
            SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
            if ("xym".equalsIgnoreCase(req.getUserName())) {
                System.out.println("Server accept client subscribe req :[" + req.toString() + "]");
                //由于使用了ProtobufEncoder，所以不需要对SubscribeRespProto.SubscribeResp进行手工编码
                ctx.writeAndFlush(resp(req.getSubReqID()));
            }
        }

        private SubscribeRespProto.SubscribeResp resp(int subReqID) {
            SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
            builder.setSubReqID(subReqID);
            builder.setRespCode("0");
            builder.setDesc("netty book order succeed ,3 days later ,sent to the designated address.");
            return builder.build();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            //发生异常，关闭链路
            ctx.close();
        }
    }

}
