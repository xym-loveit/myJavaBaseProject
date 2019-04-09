package com.xym.myJava.head_first._03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 实现自己的装饰者--将字符转为小写
 *
 * @author xym
 * @create 2019-04-09 17:20
 */
public class LowerCaseInputStream extends FilterInputStream {

    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int read = super.read();
        return read != -1 ? Character.toLowerCase(read) : read;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
