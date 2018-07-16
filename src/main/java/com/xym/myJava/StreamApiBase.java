package com.xym.myJava;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xym
 */
public class StreamApiBase {

    public static void main(String[] args) {

//        testMapToDouble();
//        testPeek();
//        testSortedAndSkipAndLimit();
//        testMapAndFilterAndDistinct();
//        testMapAndFilter();
//        testMap();
//        testFilter();
//        testFilterAndSorted();
    }

    private static void testMapToDouble() {
        List<Student> list = new ArrayList<>(Arrays.asList(new Student[]{new Student("zhangsan", 89D), new Student("lisi", 89D), new Student("wangwu", 90), new Student("jangwu", 90), new Student("zhao6", 98D)}));

        double sum = list.stream().mapToDouble(s -> s.getScore()).sum();
        System.out.println("学生分数总和=" + sum);
    }

    private static void testPeek() {
        List<Student> list = new ArrayList<>(Arrays.asList(new Student[]{new Student("zhangsan", 89D), new Student("lisi", 89D), new Student("wangwu", 90), new Student("jangwu", 90), new Student("zhao6", 98D)}));

        /**
         * peek方法主要用于调试
         */
        List<String> collect = list.stream().filter(t -> t.getScore() >= 90).peek((a) -> System.out.println(a)).map(Student::getName).collect(Collectors.toList());
        collect.forEach((n) -> System.out.println(n));
    }

    private static void testSortedAndSkipAndLimit() {
        List<Student> list = new ArrayList<>(Arrays.asList(new Student[]{new Student("zhangsan", 89D), new Student("lisi", 89D), new Student("wangwu", 90), new Student("jangwu", 90), new Student("zhao6", 98D)}));

        /**
         * 将学生列表按照分数排序，返回第3名到第5名，注意其中的skip和limit
         */
        List<Student> collect = list.stream().sorted(Comparator.comparing(Student::getScore).reversed()).skip(2).limit(3).collect(Collectors.toList());
        collect.forEach(s -> System.out.println(s.getName() + "--" + s.getScore()));
    }

    private static void testFilterAndSorted() {
        List<Student> list = new ArrayList<>(Arrays.asList(new Student[]{new Student("zhangsan", 89D), new Student("lisi", 89D), new Student("wangwu", 90), new Student("jangwu", 90), new Student("zhao6", 98D)}));

        /**
         * 过滤获取90分以上的学生，然后按照分数从高到低倒序，分数一样的按名称排序
         */
        List<Student> collect = list.stream().filter(t -> t.getScore() >= 90).sorted(Comparator.comparing(Student::getScore).reversed().thenComparing(Student::getName)).collect(Collectors.toList());

        collect.forEach((s) -> System.out.println(s.getName() + "--" + s.getScore()));

    }

    /**
     * 这种组合利用基本函数、声明式实现集合数据处理能力的编程风格即为函数式数据处理
     */
    private static void testMapAndFilterAndDistinct() {
        List<String> strings = Arrays.asList(new String[]{"abc", "def", "Hello", "aBC"});

        /**
         * 返回字符串数组中长度小于等于3的字符串，转换为小写，只保留唯一的
         */
        List<String> collect = strings.stream().filter(s -> s.length() <= 3).map(String::toLowerCase).distinct().collect(Collectors.toList());
        collect.forEach((s) -> System.out.println(s));

    }

    /**
     * 这种组合利用基本函数、声明式实现集合数据处理能力的编程风格即为函数式数据处理
     */
    private static void testMapAndFilter() {
        List<Student> list = new ArrayList<>(Arrays.asList(new Student[]{new Student("张三", 89D), new Student("李四", 89D), new Student("王五", 90), new Student("赵6", 98D)}));

        /**
         *获取分数大于90分的学生姓名
         */
        List<String> collect = list.stream().filter(t -> t.getScore() >= 90).map(Student::getName).collect(Collectors.toList());
        collect.forEach((t) -> System.out.println(t));
    }


    private static void testMap() {
        List<Student> list = new ArrayList<>(Arrays.asList(new Student[]{new Student("张三", 89D), new Student("李四", 89D), new Student("王五", 90)}));

        /**
         * 根据学生列表返回名称列表
         */
        List<String> collect = list.stream().map(Student::getName).collect(Collectors.toList());
        collect.forEach((t) -> System.out.println(t));
    }


    private static void testFilter() {
        List<Student> list = new ArrayList<>(Arrays.asList(new Student[]{new Student("张三", 89D), new Student("李四", 89D), new Student("王五", 90)}));

        /**
         * 过滤分数大于等于90的
         */
        List<Student> collect = list.stream().filter(t -> t.getScore() >= 90).collect(Collectors.toList());
        System.out.println(collect.size());
        collect.forEach((t) -> System.out.println(t.getName() + "--" + t.getScore()));
    }

    static class Student {
        private String name;
        private double score;

        public Student(String name, double score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
