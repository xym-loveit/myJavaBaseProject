package com.xym.myJava.io.nio;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-29 15:20
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }
        //服务端仅需开启一个线程
        NioTimeServerThread serverThread = new NioTimeServerThread(port);
        new Thread(serverThread, "nio-server").start();
    }
}
