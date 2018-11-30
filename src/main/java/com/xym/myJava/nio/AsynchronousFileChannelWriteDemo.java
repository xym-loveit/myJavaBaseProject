package com.xym.myJava.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * 异步文件通道写实例
 *
 * @author xym
 * @create 2018-11-30 10:43
 */
public class AsynchronousFileChannelWriteDemo {
    public static void main(String[] args) {
        Path path = Paths.get("d:/test.txt");
        try {

            //写文件之前一定要保证文件存在
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            AsynchronousFileChannel fileChannel =
                    AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long position = 0;
            buffer.put("test data".getBytes());
            buffer.flip();
            Future<Integer> operation = fileChannel.write(buffer, position);


            while (!operation.isDone()) {
            }
            buffer.clear();
            System.out.println("Write done");

            buffer.put("test data".getBytes());
            System.out.println("length=" + "test data".getBytes().length);
            System.out.println("position=" + buffer.position() + ",limit=" + buffer.limit());
            buffer.flip();

            //通过回调写
            fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("bytes written: " + result);
                    buffer.clear();
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println("Write failed");
                    exc.printStackTrace();
                }
            });

            fileChannel.close();
            System.out.println("---------------------------------main over!file close!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
