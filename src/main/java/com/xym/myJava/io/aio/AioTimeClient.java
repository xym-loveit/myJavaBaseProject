package com.xym.myJava.io.aio;

/**
 * aio客户端
 *
 * @author xym
 * @create 2018-12-29 23:49
 */
public class AioTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }
        AsyncTimeClientHandler handler = new AsyncTimeClientHandler("127.0.0.1", port);
        new Thread(handler, "aio-client").start();
    }
}
