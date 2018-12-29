package com.xym.myJava.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 请求处理类
 *
 * @author xym
 * @create 2018-12-29 11:44
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            String body = null;
            String currentTime = null;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;
            String info = "";
            while ((line = reader.readLine()) != null) {
                info += line;
                System.out.println("已接收到客户端连接");
                System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
                        + socket.getInetAddress().getHostAddress());
            }

            writer = new PrintWriter(this.socket.getOutputStream(), true);
            currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
            writer.write(currentTime);
            System.out.println("write over." + System.currentTimeMillis());
            for (int i = 0; i < 5; i++) {
                writer.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
            if (this.socket != null) {
                try {
                    this.socket.close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
