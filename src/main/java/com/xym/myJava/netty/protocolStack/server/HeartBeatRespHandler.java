package com.xym.myJava.netty.protocolStack.server;

import com.xym.myJava.netty.protocolStack.MessageType;
import com.xym.myJava.netty.protocolStack.struct.Header;
import com.xym.myJava.netty.protocolStack.struct.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * 心跳包应答
 *
 * @author xym
 * @create 2019-01-10 16:37
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat = null;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        //如果未心跳请求则返回心跳应答消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("receive client heart beat message :---->" + message);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("send heart beat response message to client :---->" + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    /**
     * 构造心跳应答消息
     *
     * @return
     */
    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
