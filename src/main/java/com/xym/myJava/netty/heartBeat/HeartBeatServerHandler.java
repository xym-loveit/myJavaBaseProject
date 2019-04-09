package com.xym.myJava.netty.heartBeat;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 1、服务端每隔5秒检测服务端的读超时，如果5秒没有接受到客户端的写请求，也就是说服务端5秒没有收到读事件，则视为一次超时
 * 2、如果超时2次则说明连接处于不活跃状态，关闭ServerChannel
 *
 * @author xym
 * @create 2019-_01-28 14:43
 */
public class HeartBeatServerHandler extends ChannelHandlerAdapter {

    private int loss_connect_time;


    /**
     * 服务端自定义事件触发，这里主要观察超时读事件
     * <p>
     * 如果超时则会触发相应的超时事件
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                loss_connect_time++;
                /**
                 * 对于客户端就是没有产生写事件
                 */
                System.out.println("5 秒没有接收到客户端的信息了");
                if (loss_connect_time > 2) {
                    System.out.println("关闭这个不活跃的channel");
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server channelRead..");
        System.out.println(ctx.channel().remoteAddress() + "->Server :" + msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
