package com.xym.myJava.oldio;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-14 15:59
 */
public class PipeExample {
    public static void main(String[] args) throws IOException {
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input = new PipedInputStream(output);
        Thread thread1 = new Thread(() -> {
            try {
                output.write("Hello world, pipe!".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            int data = -1;
            try {
                data = input.read();
                while (data != -1) {
                    System.out.print((char) data);
                    data = input.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
