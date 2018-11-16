package com.xym.myJava.oldio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-14 16:16
 */
public class FileUtils {

    public static void main(String[] args) throws IOException {
        copyFileUseNIO("d:/test.txt", "d:/test_copy.txt");
    }


    /**
     * position：跟踪已经写了多少数据或读了多少数据，它指向的是下一个字节来自哪个位置
     * limit：代表还有多少数据可以取出或还有多少空间可以写入，它的值小于等于capacity。
     * capacity：代表缓冲区的最大容量，一般新建一个缓冲区的时候，limit的值和capacity的值默认是相等的
     *
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyFileUseNIO(String src, String dest) throws IOException {

        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dest);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            //判断是否读完文件
            int read = inChannel.read(buffer);
            if (read == -1) {
                break;
            }

            //重设一下buffer的position=0，limit=position
            buffer.flip();
            //开始写
            outChannel.write(buffer);
            //写完要重置buffer，重设position=0,limit=capacity
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();
        in.close();
        out.close();
    }

}
