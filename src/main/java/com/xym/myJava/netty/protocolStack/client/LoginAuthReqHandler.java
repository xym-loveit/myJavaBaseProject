package com.xym.myJava.netty.protocolStack.client;

import com.xym.myJava.netty.protocolStack.MessageType;
import com.xym.myJava.netty.protocolStack.struct.Header;
import com.xym.myJava.netty.protocolStack.struct.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 握手认证客户端
 *
 * @author xym
 * @create 2019-_01-10 16:05
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("send msg to server ---");
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        //如果是握手认证回复消息则处理[LOGIN_RESP]，需要判断认证是否成功
        if (null != message.getHeader() && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                //握手失败，关闭连接
                ctx.close();
            } else {
                System.out.println("login is ok :" + message);
                ctx.fireChannelRead(msg);
            }
        } else {
            //如果不是，则透传
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 构建握手请求消息
     *
     * @return
     */
    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }
}
