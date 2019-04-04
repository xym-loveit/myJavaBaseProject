package com.xym.myJava.jvms;

import sun.misc.Launcher;

import java.net.URL;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-04 15:45
 */
public class LoadClassDemo {
    public static void main(String[] args) {
        //BootStrap根类加载器加载文件的路径(并非只有rt.jar)
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
    }
}
