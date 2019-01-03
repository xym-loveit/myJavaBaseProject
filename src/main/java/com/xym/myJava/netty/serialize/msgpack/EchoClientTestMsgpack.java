package com.xym.myJava.netty.serialize.msgpack;

import com.xym.myJava.netty.serialize.UserInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 对msgpack测试
 *
 * @author xym
 * @create 2019-01-02 18:09
 */
public class EchoClientTestMsgpack {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }
        new EchoClientTestMsgpack().run(port, "127.0.0.1", 1000);
    }

    public void run(int port, String host, int sendNum) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).option(ChannelOption.TCP_NODELAY, true).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .handler(/*new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //这里设置通过增加包头表示报文长度来避免粘包
                        ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
                        ch.pipeline().addLast("msgpack decoder", new io.netty.handler.codec.msgpack.MsgpackDecoder());
                        ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                        ch.pipeline().addLast("msgpack encoder", new io.netty.handler.codec.msgpack.MsgpackEncoder());
                        ch.pipeline().addLast(new EchoClientHandler(sendNum));
                    }
                }*/new ClientHandlerInit(sendNum));

        try {
            //绑定端口同步等待成功
            ChannelFuture future = bootstrap.connect(host, port).sync();
            System.out.println("已连接到服务器.....");
            //等待服务器监听端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }


    private class ClientHandlerInit extends ChannelInitializer<SocketChannel> {

        private int sendNum;

        public ClientHandlerInit(int sendNum) {
            this.sendNum = sendNum;
        }

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            //这里设置通过增加包头表示报文长度来避免粘包
            ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
            //增加解码器
            ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
            //这里设置读取报文的包头长度来避免粘包
            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
            //增加编码器
            ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
            ch.pipeline().addLast(new EchoClientHandler(sendNum));
        }

    }

    private class EchoClientHandler extends ChannelHandlerAdapter {

        private final int sendNumber;

        public EchoClientHandler(int sendNum) {
            this.sendNumber = sendNum;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            UserInfo[] userInfos = userInfos();
            for (UserInfo userInfo : userInfos) {
                //System.out.println("send user :" + userInfo);
                ctx.write(userInfo);
            }
            ctx.flush();
        }

        private UserInfo[] userInfos() {
            UserInfo[] userInfos = new UserInfo[sendNumber];
            UserInfo userInfo = null;
            for (int i = 0; i < sendNumber; i++) {
                userInfo = new UserInfo();
                userInfo.setUserId(i);
                userInfo.setUserName("ABCDEF---->" + i);
                userInfos[i] = userInfo;
            }
            return userInfos;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("Client receive the msgpack message :" + msg);
            //ctx.write(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }
}
