package com.xym.myJava.thread;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-10-15 10:50
 */
public class Main {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer, "writerTask");
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque, "cleanerTask");
        cleaner.start();
    }
}
