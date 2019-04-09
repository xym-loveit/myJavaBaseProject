package com.xym.myJava.netty.serialize.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * 采用jboss marshalling版本开发图书订购服务端
 *
 * @author xym
 * @create 2019-_01-04 10:09
 */
public class SubReqServer {


    public static void main(String[] args) {

        //InternalLoggerFactory.setDefaultFactory(new Log4JLoggerFactory());

        int port = 8080;
        try {
            if (args != null && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }
        } catch (NumberFormatException e) {
            port = 8080;
        }

        new SubReqServer().bind(port);
    }

    public void bind(int port) {
        //配置服务端nio线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100).handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //ch.pipeline().addLast("logging", new LoggingHandler(LogLevel.INFO));
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(new SubReqServerHandler());
                        }
                    });
            //绑定端口，同步等待成功
            ChannelFuture sync = bootstrap.bind(port).sync();
            System.out.println("服务器启动完成，等待客户端的连接和数据.....");
            //等待服务器监听窗口关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static class MarshallingCodeCFactory {

        /**
         * 构建JBoss Marshalling解码器MarshallingDecoder
         *
         * @return
         */
        public static MarshallingDecoder buildMarshallingDecoder() {
            MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
            MarshallingConfiguration configuration = new MarshallingConfiguration();
            configuration.setVersion(5);
            DefaultUnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, configuration);
            MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024);
            return decoder;
        }

        /**
         * 创建JBoss Marshalling编码器MarshallingEncoder
         *
         * @return
         */
        public static MarshallingEncoder buildMarshallingEncoder() {
            MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
            MarshallingConfiguration configuration = new MarshallingConfiguration();
            configuration.setVersion(5);
            DefaultMarshallerProvider provider = new DefaultMarshallerProvider(factory, configuration);
            MarshallingEncoder encoder = new MarshallingEncoder(provider);
            return encoder;
        }

    }

    private class SubReqServerHandler extends ChannelHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            //由于ProtobufDecoder已经对消息进行了自动解码，因此受到的消息可以直接使用
            SubscribeReq req = (SubscribeReq) msg;
            if ("xym".equalsIgnoreCase(req.getUserName())) {
                System.out.println("Server accept client subscribe req :[" + req.toString() + "]");
                //由于使用了ProtobufEncoder，所以不需要对SubscribeRespProto.SubscribeResp进行手工编码
                ctx.writeAndFlush(resp(req.getSubReqID()));
            }
        }

        private SubscribeResp resp(int subReqID) {
            SubscribeResp resp = new SubscribeResp();
            resp.setSubReqID(subReqID);
            resp.setRespCode("0");
            resp.setDesc("netty book order succeed ,3 days later ,sent to the designated address.");
            return resp;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            //发生异常，关闭链路
            ctx.close();
        }
    }


}
