package com.xym.myJava.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-18 16:53
 */
public class Main {
    public static void main(String[] args) {
        try {
            //hosts文件中配置hostName
            //InetAddress xym = InetAddress.getByName("xym");
            InetAddress[] xyms = InetAddress.getAllByName("xym2");
            String collect = Stream.of(xyms).map(InetAddress::toString).collect(Collectors.joining(",", "---", "----"));
            System.out.println(collect);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
