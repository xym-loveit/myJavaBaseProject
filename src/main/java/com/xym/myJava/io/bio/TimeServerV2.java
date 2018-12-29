package com.xym.myJava.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 经过优化的bio第二个版本
 *
 * @author xym
 * @create 2018-12-29 14:59
 */
public class TimeServerV2 {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = 8080;
            }
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            System.out.println("the time server is start in port :" + port);

            //为了防止来一个客户端请求就创建一个处理线程，过度消耗服务端资源。
            //可以采用固定数量的线程池缓解，可还是治标不治本
            TimeServerV2HandlerExecutePool executePool = new TimeServerV2HandlerExecutePool(50, 10000);
            while (true) {
                socket = serverSocket.accept();
                executePool.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println("the time server close.");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
