package com.xym.myJava.netty.protocol2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-_01-25 17:57
 */
public class CustomEncoder extends MessageToByteEncoder<CustomMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomMsg msg, ByteBuf out) throws Exception {
        if (null == msg) {
            throw new Exception("msg is null");
        }
        String body = msg.getBody();
        byte[] bodyBytes = body.getBytes(Charset.forName("utf-8"));
        out.writeByte(msg.getType());
        out.writeByte(msg.getFlag());
        out.writeInt(bodyBytes.length);
        out.writeBytes(bodyBytes);
    }
}
