package com.xym.myJava.io.aio;

/**
 * aio服务端
 *
 * @author xym
 * @create 2018-12-29 23:14
 */
public class AioTimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }

        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(asyncTimeServerHandler, "aio-server").start();
    }
}
