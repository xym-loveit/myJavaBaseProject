package com.xym.myJava.netty.serialize.marshalling;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

/**
 * 采用jboss marshalling版本开发图书订购客户端
 *
 * @author xym
 * @create 2019-01-04 11:16
 */
public class SubReqClient {
    public static void main(String[] args) {
        InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
        int port = 8080;
        try {
            if (args != null && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }
        } catch (NumberFormatException e) {
            port = 8080;
        }

        new SubReqClient().connect("127.0.0.1", port);
    }

    public void connect(String host, int port) {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY, true).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(SubReqServer.MarshallingCodeCFactory.buildMarshallingDecoder());
                            ch.pipeline().addLast(SubReqServer.MarshallingCodeCFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(new SubReqClientHandler());
                        }
                    });

            //发起同步链接操作
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            System.out.println("成功连接至服务器...");
            //同步等待客户端关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    private class SubReqClientHandler extends ChannelHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            for (int i = 0; i < 10; i++) {
                SubscribeReq req = subReq(i);
                ctx.write(req);
            }
            System.out.println("发送完毕。。。");
            ctx.flush();
        }

        private SubscribeReq subReq(int i) {
            SubscribeReq req = new SubscribeReq();
            req.setAddress("hangzhou xhq");
            req.setSubReqID(i);
            req.setUserName("xym");
            req.setProductName("Netty Book for Marshalling");
            return req;
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
