package com.xym.myJava.netty.heartBeat_enhance;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 读事件超时触发器
 *
 * @author xym
 * @create 2019-_01-28 16:39
 */
@ChannelHandler.Sharable
public class AcceptorIdleStateTrigger extends ChannelHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                throw new Exception("idle exception");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
