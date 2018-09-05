package com.xym.myJava.redis;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过监控客户端发送的命令来洞悉redis客户端和服务器通信的指令
 * 主要是基于resp协议
 *
 * @author xym
 * @create 2018-09-05 11:17
 */
public class RedisServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[64];
        inputStream.read(bytes);
        System.out.println(new String(bytes));
    }
}
