package com.xym.myJava.jdk8.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * 通过getResourceAsStream获取资源解答
 *
 * @author xym
 * @create 2019-06-20 9:50
 */
public class GetResourceAsStreamDemo {
    public static void main(String[] args) throws IOException {
        //one();
        //two();

    }

    /**
     * 2. Class.getClassLoader.getResourceAsStream(String path) ：默认则是从ClassPath根下获取，path不能以’/'开头，最终是由ClassLoader获取资源。
     */
    private static void two() {
        //默认从classpath根下获取，path不能以“/”开头
        InputStream inputStream = GetResourceAsStreamDemo.class.getClassLoader().getResourceAsStream("");
        try (
                Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
            lines.forEach(System.out::println);
        }
    }

    /**
     * 1. Class.getResourceAsStream(String path) ： path 不以’/'开头时默认是从此类所在的包下取资源，
     * 以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
     */
    private static void one() {
        //1、"/"表示classpath--即为代码编译输出目录--idea的话为target
        //1、InputStream inputStream = TestDemo.class.getResourceAsStream("/");
        //2、此类所在的包下取资源，即为当前类编译路径所在的包
        InputStream inputStream = GetResourceAsStreamDemo.class.getResourceAsStream("");
        try (
                Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
            lines.forEach(System.out::println);
        }
    }
}
