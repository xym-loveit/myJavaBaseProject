package com.xym.myJava.nio;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class UseCharsets {
    static public void main(String args[]) throws Exception {
        String inputFile = "d:/student.sql";
        String outputFile = "d:/student_out.sql";

        RandomAccessFile inf = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outf = new RandomAccessFile(outputFile, "rw");
        //获取读取文件长度
        long inputLength = new File(inputFile).length();

        //获取通道
        FileChannel inc = inf.getChannel();
        FileChannel outc = outf.getChannel();

        //文件映射到内存
        MappedByteBuffer inputData =
                inc.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        Charset latin1 = Charset.forName("ISO-8859-1");
        //解码器
        CharsetDecoder decoder = latin1.newDecoder();
        //编码器
        CharsetEncoder encoder = latin1.newEncoder();

        //字节解码为字符
        CharBuffer cb = decoder.decode(inputData);

        // Process char data here

        //将字符编码为字节
        ByteBuffer outputData = encoder.encode(cb);

        outc.write(outputData);

        inf.close();
        outf.close();
    }
}
