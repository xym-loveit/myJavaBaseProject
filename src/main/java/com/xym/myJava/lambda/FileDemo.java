package com.xym.myJava.lambda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * jdk8很便捷的文件操作方式
 *
 * @author xym
 * @create 2018-09-18 16:49
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        //method1();
        //method2();
        //method3();
        //method4();
    }

    private static void method4() throws IOException {
        //要注意这些方法对内存并不十分高效，因为整个文件都会读进内存。文件越大，所用的堆区也就越大。
        List<String> strings = Files.readAllLines(Paths.get("README.md"));
        strings.stream().forEach(System.out::println);

        Path write = Files.write(Paths.get("README-testWrite.md"), strings);
        System.out.println(write.toFile().getAbsolutePath());

        try (Stream<String> lines = Files.lines(Paths.get("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\java\\com\\xym\\myJava\\lambda\\FileDemo.java"))) {
            lines.filter(line -> line.contains("Files")).
                    map(String::trim).
                    forEach(System.out::println);
        }

        //如果你需要更多的精细控制，你需要构造一个新的BufferedReader来代替
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\java\\com\\xym\\myJava\\lambda\\FileDemo.java"))) {
            String line = null;
            while (Optional.ofNullable(line = bufferedReader.readLine()).isPresent()) {
                System.out.println(line);
            }
        }

        //你需要写入文件时，简单地构造一个BufferedWriter
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("abc.txt"))) {
            bufferedWriter.write("Files.newBufferedWriter test");
        }

        //BufferedReader也可以访问函数式数据流。lines方法在它所有行上面构建数据流
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\java\\com\\xym\\myJava\\lambda\\FileDemo.java"))) {
            long files = bufferedReader.lines().filter(line -> line.contains("Files")).peek(System.out::println).count();
            System.out.println(files);
        }
    }

    /**
     * 使用walk方法+filter
     */
    private static void method3() {
        Path start = Paths.get("D:\\workspace");
        int maxDepth = 10;
        Clock clock = Clock.systemDefaultZone();
        long startTime = clock.millis();
        try (Stream<Path> pathStream = Files.walk(start, maxDepth)) {
            System.out.println(pathStream.parallel().map(String::valueOf).filter(f -> f.endsWith(".js") && !f.contains("node_modules")).sorted().peek(System.out::println).count());
            //System.out.println(collect);
            long endtime = clock.millis();
            System.out.println((endtime - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接使用find方法
     */
    private static void method2() {
        //下面的例子演示了如何查找在目录及其子目录下的文件：
        Path start = Paths.get("D:\\workspace");
        int maxDepth = 10;
        Clock clock = Clock.systemDefaultZone();
        long startTime = clock.millis();
        try (Stream<Path> pathStream = Files.find(start, maxDepth, (path, attr) -> {
            //System.out.println(path.toString()+"--");
            return path.toFile().canRead() && attr.isRegularFile() && !path.toFile().isHidden() && String.valueOf(path).endsWith(".mp4");
        })) {
            System.out.println(pathStream.parallel().map(String::valueOf).sorted().peek((s) -> {
            }).count());
            //System.out.println(collect);
            long endtime = clock.millis();
            System.out.println((endtime - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void method1() {
        //列出了当前工作目录的所有文件，之后将每个路径都映射为它的字符串表示。之后结果被过滤、排序，最后连接为一个字符串
        try (Stream<Path> pathStream = Files.list(Paths.get("d:/"))) {
            System.out.println(pathStream.
                    map(String::valueOf).
                    filter(path -> !path.startsWith(".")).
                    sorted().
                    collect(Collectors.joining("; ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
