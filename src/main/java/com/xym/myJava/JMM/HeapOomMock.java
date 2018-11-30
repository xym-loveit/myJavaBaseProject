package com.xym.myJava.JMM;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出模拟
 * OutOfMemoryError
 * <p>
 * vm args:-Xms16m -Xmx16m
 *
 * @author xym
 * @create 2018-11-30 14:08
 */
public class HeapOomMock {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        int i = 0;
        boolean flag = true;
        while (flag) {
            try {
                i++;
                list.add(new byte[1024 * 1024]);//每次增加一个1M大小的数组对象
            } catch (Throwable e) {
                e.printStackTrace();
                flag = false;
                System.out.println("count=" + i);//记录运行的次数
            }
        }
    }

}
