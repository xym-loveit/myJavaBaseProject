package com.xym.myJava.lambda;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-18 16:24
 */
public class StringDemo {
    public static void main(String[] args) {
        //字符串连接
        System.out.println(String.join("|", "aaa", "ccc", "d", "f"));
        //第二个方法chars从字符串所有字符创建数据流，所以你可以在这些字符上使用流式操作。
        System.out.println(String.join("|", "aaa", "ccc", "d", "f").chars().distinct().mapToObj(c -> {
            return String.valueOf((char) c);
        }).sorted().collect(Collectors.joining()));

        String join = String.join("|", "aaa", "ccca", "d", "f");
        //正则表达式也可以使用流操作
        System.out.println(Pattern.compile("\\|").splitAsStream(join).filter(s -> s.contains("a")).sorted().collect(Collectors.joining(":")));


        //正则表达式可以转换为谓词，用来过滤字符串流
        Pattern pattern = Pattern.compile(".*@gmail\\.com");
        System.out.println(Stream.of("bob@gmail.com", "alice@hotmail.com")
                .filter(pattern.asPredicate())
                .count());

        //数字溢出更好的支持
        try {
            int i = Math.addExact(Integer.MAX_VALUE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
