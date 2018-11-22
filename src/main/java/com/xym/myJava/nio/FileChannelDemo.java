package com.xym.myJava.nio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件通道
 *
 * @author xym
 * @create 2018-11-19 13:36
 */
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        FileChannel channel = null;
        try {
            RandomAccessFile accessFile = new RandomAccessFile("d:/thread.log", "rw");
            //通过RandomAccessFile打开FileChannel
            channel = accessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //通过read方法读取数据
            channel.read(buffer);
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            System.out.println(new String(bytes));
            System.out.println("写入数据前文件大小：" + channel.size());
            ByteBuffer wrap = ByteBuffer.wrap("mnbvcxzlkjhgfdsa".getBytes());
            int writeBytes = 0;
            //使用while循环，确保消费所有数据
            while (wrap.hasRemaining()) {
                //通道通过buffer写数据
                int len = channel.write(wrap);
                writeBytes += len;
            }
            System.out.println(writeBytes);
            System.out.println("写入数据前后文件大小：" + channel.size());
            //截取文件的前48个字节
            FileChannel truncate = channel.truncate(48);
            System.out.println(truncate.size() + "--" + truncate.position() + "--");
            //强制将数据刷到磁盘上
            truncate.force(true);
            truncate.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //通道操作完毕后需要关闭
            channel.close();
        }

    }
}
