package com.xym.myJava.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-08 0:40
 */
public class ReadFile {
    public static void main(String[] args) {
        try {
            Stream<String> lines = Files.lines(Paths.get("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\java\\com\\xym\\myJava\\base\\Counter.java"));
            lines.filter(s -> null != s && !s.trim().equals("")).map(s ->s.length()).forEach(System.out::println);
            //List<String> collect = lines.collect(Collectors.toList());
            //for (String s : collect) {
            //    System.out.println(s);
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
