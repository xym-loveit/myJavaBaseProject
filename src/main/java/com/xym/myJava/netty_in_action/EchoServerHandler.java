package com.xym.myJava.netty_in_action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * 口令回复服务端ChannelHandler
 *
 * @author xym
 * @create 2019-_01-18 9:36
 */

/**
 * Sharable注解标示一个Channel-Handler 可以被多个 Channel 安全地共享
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        String msgStr = in.toString(CharsetUtil.UTF_8);
        System.out.println("server received:" + msgStr);
        //将接受到的消息发送给发送者
        ByteBuf buf = Unpooled.copiedBuffer(("server-" + msgStr.substring(msgStr.indexOf("-") + 1)).getBytes());
        ctx.write(buf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息冲刷到远程节点，并且关闭该 Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈，并关闭该channel
        cause.printStackTrace();
        ctx.close();
    }
}
