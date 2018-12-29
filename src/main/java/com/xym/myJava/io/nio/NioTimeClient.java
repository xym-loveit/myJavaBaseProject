package com.xym.myJava.io.nio;

/**
 * NIO客户端
 *
 * @author xym
 * @create 2018-12-29 16:13
 */
public class NioTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }
        NioTimeClientThread clientThread = new NioTimeClientThread("127.0.0.1", port);
        new Thread(clientThread, "nio-client").start();
    }
}
