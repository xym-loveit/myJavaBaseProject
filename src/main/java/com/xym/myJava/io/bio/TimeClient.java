package com.xym.myJava.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 *
 * @author xym
 * @create 2018-12-29 13:43
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                //采用默认值
                port = 8080;
            }
        }
        Socket socket = null;
        PrintWriter out = null;
        //BufferedReader reader = null;
        InputStream in = null;
        try {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("query time order");
            System.out.println("Send order 2 server succeed." + System.currentTimeMillis());
            //reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            in = socket.getInputStream();
            //String info = "";
            //String line = snull;
            byte[] bytes = new byte[128];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                String str = new String(bytes, 0, len);
                System.out.println("客户端接收服务端发送信息：" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            //if (reader != null) {
            //    try {
            //        reader.close();
            //        reader = null;
            //    } catch (IOException e) {
            //        e.printStackTrace();
            //    }
            //}
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
