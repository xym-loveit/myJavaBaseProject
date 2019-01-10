package com.xym.myJava.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-01-10 10:09
 */
public class WebSocketServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }
        new WebSocketServer().run(port);
    }

    public void run(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("http-codec", new HttpServerCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                        pipeline.addLast("handler", new WebSocketServerHandler());
                    }
                });
        try {
            //同步绑定
            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("Web socket server started at port " + port + ".");
            System.out.println("Open your browser and navigate to http://localhost:" + port + "/");
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class WebSocketServerHandler extends SimpleChannelInboundHandler {

        private WebSocketServerHandshaker handshaker = null;

        @Override
        protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
            //传统Http接入(第一次握手需要http协议支持)
            if (msg instanceof FullHttpMessage) {
                FullHttpRequest msg_01 = (FullHttpRequest) msg;
                System.out.println("http headers:" + msg_01.headers());
                handleHttpRequest(ctx, (FullHttpRequest) msg);
                //Websocket 接入
            } else if (msg instanceof WebSocketFrame) {
                WebSocketFrame msg_01 = (WebSocketFrame) msg;
                System.out.println("WebSocketFrame :" + msg_01.toString());
                handleWebSocketFrame(ctx, (WebSocketFrame) msg);
            }
        }

        private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {

            //如果Http解码失败，返回Http异常
            if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
                sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
                return;
            }

            WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
            //创建握手处理类
            handshaker = handshakerFactory.newHandshaker(request);
            if (handshaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshaker.handshake(ctx.channel(), request);
            }
        }


        private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
            //判断是否是关闭链路指令
            if (frame instanceof CloseWebSocketFrame) {
                CloseWebSocketFrame frame_clo = (CloseWebSocketFrame) frame;
                System.out.println("WebSocket通道关闭中..." + frame_clo.toString());
                handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
                return;
            }

            //判断是否是PING消息
            if (frame instanceof PingWebSocketFrame) {
                System.out.println("---ping pong----");
                ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
                return;
            }

            //本例仅支持文本消息，不支持二进制
            if (!(frame instanceof TextWebSocketFrame)) {
                throw new UnsupportedOperationException(String.format("% s frame types not supported", frame.getClass().getName()));
            }

            String body = ((TextWebSocketFrame) frame).text();
            System.out.println(String.format("%s received %s", ctx.channel(), body));
            ctx.channel().write(new TextWebSocketFrame(body + ",欢迎使用Netty WebSocket服务，现在时刻：" + new Date().toString()));
        }

        private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
            //返回应答给客户端
            if (response.status().code() != 200) {
                ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
                response.content().writeBytes(buf);
                buf.release();
                //设置响应信息
                HttpHeaderUtil.setContentLength(response, response.content().readableBytes());
            }
            //如果是非Kepp-Alive，关闭连接
            ChannelFuture future = ctx.channel().writeAndFlush(response);
            if (!HttpHeaderUtil.isKeepAlive(request) || response.status().code() != 200) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
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
