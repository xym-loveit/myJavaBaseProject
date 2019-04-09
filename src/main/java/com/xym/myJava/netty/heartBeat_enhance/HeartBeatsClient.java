package com.xym.myJava.netty.heartBeat_enhance;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-_01-28 16:57
 */
public class HeartBeatsClient {

    protected final HashedWheelTimer timer = new HashedWheelTimer();
    private Bootstrap boot;
    private final ConnectorIdleStateTrigger idleStateTrigger = new ConnectorIdleStateTrigger();


    public void connet(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        boot = new Bootstrap();
        boot.group(group).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO));
        final ConnectionWatchdog watchdog = new ConnectionWatchdog(boot, timer, port, host, true) {
            @Override
            public ChannelHandler[] handlers() {
                return new ChannelHandler[]{
                        this,
                        new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS),
                        idleStateTrigger,
                        new StringDecoder(),
                        new StringEncoder(),
                        new HeartBeatClientHandler()
                };
            }
        };

        ChannelFuture future;

        try {
            synchronized (boot) {
                boot.handler(new ChannelInitializer<Channel>() {
                    //初始化channel
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(watchdog.handlers());
                    }
                });
                future = boot.connect(host, port);
            }

            // 以下代码在synchronized同步块外面是安全的
            ChannelFuture sync = future.sync();
            sync.channel().closeFuture().sync();
        } catch (Throwable t) {
            throw new Exception("connects to  fails", t);
        }

    }


    public static void main(String[] args) throws Exception {
        new HeartBeatsClient().connet(8080, "127.0.0.1");
    }
}
