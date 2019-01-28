package com.xym.myJava.netty.heartBeat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

/**
 * 1、客户端在没有写出时，会产生超时写事件
 * <p>
 * 2、在超时写事件发生时，会往服务端写数据（创造写事件），但指定了只发生3次
 * <p>
 * 3、在超出指定的3次后，则不会产生任何写事件，此时对于服务端来说便会产生读事件超时
 * <p>
 * 4、注意：服务端的读事件设定的时间为5秒，客户端的写事件超时时间为4秒（设置的要比服务端超时时间【短】）
 *
 * @author xym
 * @create 2019-01-28 15:16
 */
public class HeartBeatClientHandler extends ChannelHandlerAdapter {

    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",
            CharsetUtil.UTF_8));

    private static final int TRY_TIMES = 3;

    private int currentTime = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活时间是：" + new Date());
        System.out.println("HeartBeatClientHandler channelActive");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("停止时间是：" + new Date());
        System.out.println("HeartBeatClientHandler channelInactive");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("循环触发时间：" + new Date());
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            //没有写事件的时候，一超时就会触发改请求
            if (event.state() == IdleState.WRITER_IDLE) {
                /**
                 * 如果三次之内没有写请求，就触发写事件（对于服务端就是读事件）,否则就什么也不处理
                 */
                if (currentTime <= TRY_TIMES) {
                    System.out.println("currentTime:" + currentTime);
                    currentTime++;
                    ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
                }
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        System.out.println(message);
        if (message.equals("Heartbeat")) {
            ctx.write("has read message from server");
            ctx.flush();
        }
        ReferenceCountUtil.release(msg);
    }
}
