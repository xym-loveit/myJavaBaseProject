package com.xym.myJava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-15 15:54
 */
public class FastCopyFile2 {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("参数传入有误：Useage Java FastCopyFile2 infile outfile");
            System.exit(1);
        }
        System.out.println("开始复制文件----");
        long start = System.currentTimeMillis();
        //创建直接缓冲区，每一次调用底层操作系统的本机 I/O 操作之前(或之后)，尝试避免将缓冲区的内容拷贝到一个中间缓冲区中
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 2);
        try (FileInputStream inputStream = new FileInputStream(args[0]);
             FileOutputStream outputStream = new FileOutputStream(args[1]);
             FileChannel channel1 = inputStream.getChannel();
             FileChannel channel2 = outputStream.getChannel()) {
            while (true) {
                //position = 0;
                // limit = capacity;
                //腾出容量供写入数据
                //切换为读取
                buffer.clear();
                //视为有数据
                if (channel1.read(buffer) > 0) {
                    //limit = position;
                    //position = 0;
                    //修改位置，供全量读取数据
                    buffer.flip();//读切换为写
                    channel2.write(buffer);
                } else {
                    //读取完毕退出循环
                    break;
                }
            }
        }
        System.out.println("复制文件结束----耗时=" + (System.currentTimeMillis() - start));
    }
}
