package com.xym.myJava;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-28 17:54
 */
public class PathMain {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".\\src\\main\\java\\com\\xym\\myJava\\PathMain.java");
        System.out.println(path.getFileName());
        System.out.println(path.getNameCount());
        System.out.println(path.getParent());
        System.out.println(path.getRoot());
        System.out.println(path.isAbsolute());
        System.out.println(path.startsWith("D:\\"));
        System.out.println(path.toString());
        System.out.println(Paths.get(".").toAbsolutePath());
        System.out.println(Paths.get(".").toAbsolutePath().normalize());
        System.out.println(Paths.get(".").toRealPath());
        //转换相对路径
        System.out.println(Paths.get("..").toAbsolutePath());
        System.out.println(Paths.get("..").toAbsolutePath().normalize());
        System.out.println(Paths.get("..").toRealPath());
    }
}
