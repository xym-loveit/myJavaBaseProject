package io.netty.handler.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * msgpack编码器
 *
 * @author xym
 * @create 2019-_01-02 17:52
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    /**
     * msgpack负责将Object类型的POJO对象编码为byte数组
     * 然后写入到ByteBuf中
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(msg);
        out.writeBytes(bytes);
    }
}
