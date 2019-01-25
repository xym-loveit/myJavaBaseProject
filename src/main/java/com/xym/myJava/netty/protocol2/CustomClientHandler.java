package com.xym.myJava.netty.protocol2;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-01-25 17:49
 */
public class CustomClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CustomMsg customMsg = new CustomMsg((byte) 0xAB, (byte) 0xCD, "Hello,Netty".length(), "Hello,Netty");
        ctx.writeAndFlush(customMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof CustomMsg) {
            CustomMsg customMsg = ((CustomMsg) msg);
            System.out.println("receive server msg=" + customMsg.getBody());
        }
    }
}
