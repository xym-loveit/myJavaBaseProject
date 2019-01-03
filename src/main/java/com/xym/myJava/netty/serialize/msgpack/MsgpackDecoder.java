package com.xym.myJava.netty.serialize.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * msgpack解码器,将解码器，放入指定的目录位置（package io.netty.handler.codec.msgpack下）
 *
 * @author xym
 * @create 2019-01-02 17:59
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    /**
     * 从数据包msg中获取需要解码的byte数组，然后调用MessagePack的read方法将其反序列化为Object对象
     * 将解码后的对象加入到解码列表out中
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int len = msg.readableBytes();
        final byte[] bytes = new byte[len];
        msg.getBytes(msg.readerIndex(), bytes, 0, len);
        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(bytes));
    }
}
