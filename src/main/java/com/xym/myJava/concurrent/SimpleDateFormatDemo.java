package com.xym.myJava.concurrent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat单例在多线程情况下，会出现线程安全问题（java.lang.NumberFormatException: For input string: ""），
 * <p>
 * 可通过以下方式进行处理
 * 1、可以通过synchronized同步锁来保证线程安全
 * 2、可以通过ThreadLocal来保证每个线程使用自己的副本
 *
 * @author xym
 * @create 2019-03-21 16:12
 */
public class SimpleDateFormatDemo {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static final ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    //synchronized (format) {
                    //使用单例日期实例解析文本
                    //System.out.println(format.parse("2019-03-21 16:15:24"));
                    System.out.println(THREAD_LOCAL.get().parse("2019-03-21 16:15:24"));
                    //}
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }
}
