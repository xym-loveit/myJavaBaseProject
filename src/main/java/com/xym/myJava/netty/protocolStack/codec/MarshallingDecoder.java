package com.xym.myJava.netty.protocolStack.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-_01-10 15:43
 */
public class MarshallingDecoder {

    private final Unmarshaller unmarshaller;


    public MarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    /**
     * @param in
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected Object decode(ByteBuf in) throws IOException, ClassNotFoundException {
        try {
            //1. 读取第一个4bytes，里面放置的是object对象的byte长度
            int objSize = in.readInt();
            //跳过消息长度
            ByteBuf buf = in.slice(in.readerIndex(), objSize);
            //2 . 使用bytebuf的代理类
            ByteInput input = new ChannelBufferByteInput(buf);
            //3. 开始解码
            unmarshaller.start(input);
            Object object = unmarshaller.readObject();
            unmarshaller.finish();
            //4. 读完之后设置读取的位置
            in.readerIndex(in.readerIndex() + objSize);
            return object;
        } finally {
            unmarshaller.close();
        }
    }

}
