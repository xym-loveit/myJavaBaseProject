package com.xym.myJava.redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 手动开发一个redis客户端
 * 主要采用redis的RESP协议，发送服务端可以识别的命令即可
 * 参见 https://www.cnblogs.com/rjzheng/p/9347749.html
 *
 * @author xym
 * @create 2018-09-05 11:47
 */
public class RedisClientByJava {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public static final String WINDOWS_CTRL = "\r\n";

    public RedisClientByJava(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            inputStream = this.socket.getInputStream();
            outputStream = this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String set(String key, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("*3").append(WINDOWS_CTRL);
        sb.append("$3").append(WINDOWS_CTRL);
        sb.append("SET").append(WINDOWS_CTRL);
        sb.append("$").append(key.length()).append(WINDOWS_CTRL);
        sb.append(key).append(WINDOWS_CTRL);
        sb.append("$").append(value.length()).append(WINDOWS_CTRL);
        sb.append(value).append(WINDOWS_CTRL);
        byte[] bytes = new byte[1024];
        try {
            outputStream.write(sb.toString().getBytes());
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

    public static void main(String[] args) {
        String eat = new RedisClientByJava("127.0.0.1", 6379).set("eat", "123456");
        System.out.println(eat);
    }
}
