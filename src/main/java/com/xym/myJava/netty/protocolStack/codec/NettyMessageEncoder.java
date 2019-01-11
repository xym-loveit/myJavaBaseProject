package com.xym.myJava.netty.protocolStack.codec;

import com.xym.myJava.netty.protocolStack.struct.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * NettyMessage消息编码类
 *
 * @author xym
 * @create 2019-01-10 15:00
 */
public final class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("the encode message is null");
        }
        //---写入crcCode---
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        //---写入length---
        sendBuf.writeInt(msg.getHeader().getLength());
        //---写入sessionId---
        sendBuf.writeLong(msg.getHeader().getSessionID());
        //---写入type---
        sendBuf.writeByte(msg.getHeader().getType());
        //---写入priority---
        sendBuf.writeByte(msg.getHeader().getPriority());
        //---写入附件大小---
        sendBuf.writeInt(msg.getHeader().getAttachment().size());
        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> entry : msg.getHeader().getAttachment().entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            marshallingEncoder.encode(value, sendBuf);
        }

        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
        }
        //之前写了crcCode 4bytes，除去crcCode和length 8bytes即为更新之后的字节
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
