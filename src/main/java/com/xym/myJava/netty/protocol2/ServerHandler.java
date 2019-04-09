package com.xym.myJava.netty.protocol2;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-_01-25 17:44
 */
public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof CustomMsg) {
            CustomMsg customMsg = (CustomMsg) msg;
            System.out.println("Client->Server:" + ctx.channel().remoteAddress() + " send " + customMsg.getBody());
            ctx.writeAndFlush(customMsg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
