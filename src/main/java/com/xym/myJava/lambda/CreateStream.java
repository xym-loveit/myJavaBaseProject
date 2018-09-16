package com.xym.myJava.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-16 14:47
 */
public class CreateStream {
    public static void main(String[] args) {

        //createStreamByArray();
        //createStreamByFile();
        //createStreamGen();
        //createStreamBySupplier();
        //createStreamByIterateate();
        //createStreamByIntStream();
        //createStreamByLongStream();
    }

    /**
     * 通过LongStream创建流，并去重             distinct
     */
    private static void createStreamByLongStream() {
        LongStream.of(1, 2, 3, 4, 5, 6, 7, 7, 8, 8, 9, 9).distinct().forEach(System.out::println);
    }

    /**
     * 通过IntStream创建                   skip
     */
    private static void createStreamByIntStream() {
        IntStream.range(1, 10).skip(2).forEach(System.out::println);
    }

    /**
     * 通过迭代器创建
     */
    private static void createStreamByIterate() {
        Stream.iterate(0, (n) -> n + 2).limit(100).forEach(System.out::println);
    }

    /**
     * 通过供应者创建
     */
    private static void createStreamBySupplier() {
        Random random = new Random(System.currentTimeMillis());

        Stream.generate(() -> {
            return random.nextInt(1000);
        }).limit(100).forEach(System.out::println);
    }

    /**
     * 通过of方式创建流
     */
    private static void createStreamGen() {
        Stream.of("hello", "xym", "loveit").forEach(s -> System.out.println(s));
    }

    /**
     * 通过文件创建流
     */
    private static void createStreamByFile() {
        Path path = Paths.get("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\java\\com\\xym\\myJava\\lambda\\CreateStream.java");
        try {
            Stream<String> lines = Files.lines(path);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过数组创建流
     */
    private static void createStreamByArray() {
        Arrays.stream(new String[]{"a", "b", "c", "d"}).forEach(System.out::println);
    }

}
