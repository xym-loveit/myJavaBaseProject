package com.xym.myJava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-29 16:27
 */
public class MemMapReadWrite {

    private static int len;

    public static void main(String[] args) {
        String readFile = "d:/socketChannel.out";
        String writeFile = "d:/socketChannel_copy.out";
        writeFile(readFile, writeFile);
    }

    /**
     * 读文件
     *
     * @param fileName
     * @return
     */
    public static ByteBuffer readFile(String fileName) {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            len = (int) file.length();
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, len);

            return buffer.get(new byte[(int) file.length()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeFile(String readFileName, String writeFileName) {
        try {
            RandomAccessFile file = new RandomAccessFile(writeFileName, "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = readFile(readFileName);
            MappedByteBuffer bytebuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, len);
            long startTime = System.currentTimeMillis();
            System.out.println("len=" + len);
            //for (int i = 0; i < len; i++) {
                bytebuffer.put(buffer);
            //}
            //bytebuffer.flip();
            long endTime = System.currentTimeMillis();
            System.out.println("写文件耗时： " + (endTime - startTime));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
