package com.xym.myJava.netty.protocolStack.server;

import com.xym.myJava.netty.protocolStack.MessageType;
import com.xym.myJava.netty.protocolStack.struct.Header;
import com.xym.myJava.netty.protocolStack.struct.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 握手和安全认证
 *
 * @author xym
 * @create 2019-_01-10 16:05
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    /**
     * 存储已经连接的客户端
     */
    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();
    /**
     * 白名单
     */
    private String[] whiteList = {"127.0.0.1"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        //如果是握手请求则处理
        if (null != message.getHeader() && message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp = null;
            //重复登录，拒绝
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResp((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOK = false;
                for (String s : whiteList) {
                    if (s.equals(ip)) {
                        isOK = true;
                        break;
                    }
                }

                loginResp = buildResp((byte) (isOK ? 0 : -1));
                if (isOK) {
                    nodeCheck.put(nodeIndex, true);
                }
            }
            System.out.println("the login response is :" + loginResp + " body [" + loginResp.getBody() + "]");
            ctx.writeAndFlush(loginResp);
        } else {
            //如果不是握手消息，透传
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //删除缓存
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 构造认证响应信息
     *
     * @param result
     * @return
     */
    private NettyMessage buildResp(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }
}
