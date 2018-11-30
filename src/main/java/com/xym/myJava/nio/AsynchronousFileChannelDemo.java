package com.xym.myJava.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * 异步文件通道读实例
 *
 * @author xym
 * @create 2018-11-30 9:57
 */
public class AsynchronousFileChannelDemo {
    public static void main(String[] args) {
        Path path = Paths.get("d:/test.txt");
        try {

            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long position = 0;

            //异步读取
            Future<Integer> operation = fileChannel.read(buffer, position);
            //循环判断是否读取完毕
            while (!operation.isDone()) {
                //等待读取动作完成
            }
            //只有读取完毕才进行输出
            buffer.flip();
            //byte[] data = new byte[buffer.limit()];
            //buffer.get(data);
            System.out.println(new String(buffer.array()));
            buffer.clear();

            //第二种方式，通过回调
            fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("result = " + result);

                    attachment.flip();
                    byte[] data = new byte[attachment.limit()];
                    attachment.get(data);
                    System.out.println(new String(data));
                    attachment.clear();
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {

                }
            });

            fileChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
