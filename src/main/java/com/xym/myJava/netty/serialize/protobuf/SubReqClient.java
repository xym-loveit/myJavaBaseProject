package com.xym.myJava.netty.serialize.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.util.ArrayList;

/**
 * protobuf版本图书订购客户端
 *
 * @author xym
 * @create 2019-01-03 17:58
 */
public class SubReqClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }

        new SubReqClient().connect("127.0.0.1", 8080);
    }

    private void connect(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).
                option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(new SubReqClientHandler());
                    }
                });

        try {
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            System.out.println("成功连接至服务器...");
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅关闭线程组
            group.shutdownGracefully();
        }
    }

    private class SubReqClientHandler extends ChannelHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            for (int i = 0; i < 10; i++) {
                SubscribeReqProto.SubscribeReq subscribeReq = subReq(i);
                ctx.write(subscribeReq);
            }
            ctx.flush();
        }

        private SubscribeReqProto.SubscribeReq subReq(int i) {
            SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
            builder.setSubReqID(i);
            builder.setUserName("xym");
            builder.setProductName("Netty Book for Protobuf");
            ArrayList<String> address = new ArrayList<>();
            address.add("Nanjing YuHuaTai");
            address.add("Beijing xxx");
            address.add("Shanghai yyyy");
            builder.addAllAddress(address);
            return builder.build();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            //从服务器端的响应读取信息
            System.out.println("Receive server response :[" + msg + "]");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
