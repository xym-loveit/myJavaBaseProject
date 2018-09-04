package com.xym.myJava.myserver;

import java.io.IOException;
import java.io.InputStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 17:52
 */
public class HttpRequest {
    private String uri;

    public HttpRequest(InputStream in) throws IOException {
        resolverRequest(in);
    }

    public String getUri() {
        return uri;
    }

    private void resolverRequest(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        int len = inputStream.read(buff);
        if (len > 0) {
            String msg = new String(buff, 0, len);
            System.out.println("====" + msg + "======");
            // 解析出来 uri
            uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1") - 1);
            System.out.println("uri-----" + uri);
        } else {
            System.out.println("bad Request!");
        }
    }


}
