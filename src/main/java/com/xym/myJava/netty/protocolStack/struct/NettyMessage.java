package com.xym.myJava.netty.protocolStack.struct;

/**
 * Netty自定义协议栈，消息结构
 *
 * @author xym
 * @create 2019-_01-10 14:49
 */
public final class NettyMessage {

    /**
     * 消息头
     */
    private Header header;
    /**
     * 消息体
     */
    private Object body;

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
