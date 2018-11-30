package com.xym.myJava.JMM;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法区内存溢出：构造大量的类
 * <p>
 * 永久代溢出
 * <p>
 * 必须在jdk1.7 以下运行，因为在 JDK 1.8 中， HotSpot 已经没有 “PermGen space”这个区间了，取而代之是一个叫做 Metaspace（元空间）
 *
 * @author xym
 * @create 2018-11-30 14:13
 */
public class PermGenOomMock {
    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = Paths.get("D:\\workspace\\IdeaProjects\\myJavaBaseProject").toUri().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.xym.myJava.JMM.Test");
            }
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
