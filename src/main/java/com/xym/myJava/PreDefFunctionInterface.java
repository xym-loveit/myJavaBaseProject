package com.xym.myJava;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 预定义函数式接口使用
 *
 * @author xym
 */
public class PreDefFunctionInterface {
    public static void main(String[] args) {



//        refStaticFunction();
//        useFunction();
//        userConsumer();
    }

    /**
     * 静态方法引用
     */
    private static void refStaticFunction() {
        Supplier<String> getCollegeName = Student::getCollegeName;
        System.out.println(getCollegeName.get());

        Supplier<String> getCollegeName2 = () -> Student.getCollegeName();
        System.out.println(getCollegeName2.get());
    }

    /**
     * 实例方法引用
     */
    private static void refInstanceFunction() {
        Function<Student, String> getCollegeName = Student::getName;
        Function<Student, String> getCollegeName2 = (Student s) -> s.getName();
    }

    /**
     * 方法引用
     */
    private static void refFunction() {
        List<Student> students = new ArrayList<>(Arrays.asList(new Student[]{new Student("张三", 89D), new Student("李四", 89D), new Student("wangwu", 95D)}));

        List<String> map = map(students, t -> t.getName());

        List<String> map1 = map(students, Student::getName);
    }

    private static void userConsumer() {
        List<Student> students = new ArrayList<>(Arrays.asList(new Student[]{new Student("张三", 89D), new Student("李四", 89D), new Student("wangwu", 95D)}));

        for (Student student : students) {
            System.out.println(student + "--" + student.getName() + "--" + student.getScore());
        }

        /**
         * 在不创建新对象，直接在原对象上做修改。使用Consumer函数式接口
         */
        process(students, student -> student.setScore(student.getScore() - 10));

        for (Student student : students) {
            System.out.println(student + "--" + student.getName() + "--" + student.getScore());
        }
    }


    /**
     * 使用函数转换
     */
    private static void useFunction() {

        List<Student> students = new ArrayList<>(Arrays.asList(new Student[]{new Student("张三", 89D), new Student("李四", 89D), new Student("wangwu", 95D)}));

        /**
         * 提取学生名称
         */
        List<String> list = map(students, t -> t.getName());
        for (String s : list) {
            System.out.println(s);
        }

        /**
         * 统一将学生分数减10分
         */
        List<Student> map = map(students, t -> new Student(t.getName(), t.getScore() - 10));
        for (Student s : map) {
            System.out.println(s.getName() + "--" + s.getScore());
        }
    }


    /**
     * 使用谓词过滤
     */
    private static void usePredicate() {
        List<Student> students = new ArrayList<>(Arrays.asList(new Student[]{new Student("张三", 89D), new Student("李四", 89D), new Student("wangwu", 95D)}));

        //过滤90分以上的学生
        List<Student> filter = filter(students, t -> t.getScore() > 90);
        filter.forEach(s -> System.out.println(s.getName() + "--" + s.getScore()));
    }

    /**
     * 根据谓词过滤集合，具备一定的通用性
     *
     * @param list
     * @param pred
     * @param <E>
     * @return
     */
    public static <E> List<E> filter(List<E> list, Predicate<E> pred) {
        List<E> retList = new ArrayList<>();
        for (E e : list) {
            if (pred.test(e)) {
                retList.add(e);
            }
        }
        return retList;
    }

    /**
     * @param list
     * @param consumer
     * @return
     */
    public static <E> void process(List<E> list, Consumer<E> consumer) {
        for (E e : list) {
            consumer.accept(e);
        }
    }

    /**
     * @param list
     * @param mapper
     * @param <T>    输入
     * @param <R>    输出
     * @return
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> retList = new ArrayList<>();
        for (T t : list) {
            retList.add(mapper.apply(t));
        }
        return retList;
    }

    static class Student {
        private String name;
        private double score;

        public static String getCollegeName() {
            return "Laoma School";
        }

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
    }
}
