package com.xym.myJava.jdk8.lambda;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-21 14:46
 */
public class ExceptionDemo3 {


    public static <T> Supplier<T> unchecked(Callable<T> f) {
        return () -> {
            try {
                return f.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable t) {
                System.out.println("----------------");
                throw t;
            }
        };
    }


    public static void main(String[] args) {
        Supplier<String> s = unchecked(() -> {
            String data = new String(Files.readAllBytes(
                    Paths.get("d:/雪豹突击队.txt")), StandardCharsets.UTF_8);
            if (1 == 1) {
                throw new Exception("----");
            }
            return data;
        });
        System.out.println(s.get());
    }
}
