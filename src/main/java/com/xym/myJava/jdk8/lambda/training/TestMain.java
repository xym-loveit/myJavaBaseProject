package com.xym.myJava.jdk8.lambda.training;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-21 22:26
 */
public class TestMain {
    public static void main(String[] args) {
        //listFiles();
        //listFile2();
        //listFile();
        //fileNameSort();
        //andthen();
        //forUnSupport();
        Collection2Impl collect = IntStream.range(1, 20).boxed().map(String::valueOf).collect(Collectors.toCollection(Collection2Impl::new));
        collect.forEachIf(System.out::println, (s) -> Integer.parseInt(s.toString()) > 10);
    }

    interface Collection2<T> extends Collection {
        default <T> void forEachIf(Consumer<T> action, Predicate<T> predicate) {
            Iterator<T> iterator = iterator();
            while (iterator.hasNext()) {
                T next = iterator.next();
                if (predicate.test(next)) {
                    action.accept(next);
                }
            }
        }
    }

    private static void forUnSupport() {
        String[] s = "abc".split("");
        System.out.println(s.length);
        List<Runnable> list = new ArrayList<>();
        for (String s1 : s) {
            list.add(() -> System.out.println(s1));
        }
        list.forEach(r -> r.run());
        list.clear();
        //for (int i = 0; i < s.length; i++) {
        //无法遍历
        //list.add(() -> System.out.println(s[i]));
        //}
    }

    private static void andthen() {
        //将2个runnable变为了一个
        BinaryOperator<Runnable> andThen = (r1, r2) -> () -> {
            r1.run();
            r2.run();
        };
        Runnable apply = andThen.apply(() -> System.out.println("run1"), () -> System.out.println("run2"));
        apply.run();
    }

    private static void fileNameSort() {
        String src = "d://soft";
        try {
            Files.walk(Paths.get(src), 4).
                    sorted(Comparator.comparing(p -> p.getParent().toString())).
                    sorted(Comparator.comparing(Path::getFileName)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listFile() {
        String pathname = "d://soft";
        File file = new File(pathname);
        String[] list = file.list((File dir, String name) -> {
            if (name.endsWith(".pdf")) {
                return true;
            }
            return false;
        });
        System.out.println(Arrays.toString(list));
    }

    private static void listFile2() {
        String src = "d://soft";
        File file = new File(src);
        File[] files = file.listFiles(File::isDirectory);
        System.out.println(Arrays.stream(files).collect(Collectors.toList()));
    }


    private static void listFiles() {
        String src = "d://soft";
        File file = new File(src);
        File[] files = file.listFiles(f -> {
            if (f.isDirectory()) {
                //System.out.println(f.getName());
                return true;
            } else {
                //System.out.println("----" + name);
            }
            return false;
        });
        System.out.println(Arrays.stream(files).collect(Collectors.toList()));
    }
}
