package com.xym.myJava.jdk8optional;

import java.util.Optional;

/**
 * Optional测试
 *
 * @author xym
 * @create 2018-09-04 16:56
 */
public class OptionalMain {
    public static void main(String[] args) {
        //group1();
        //group2();
        //group3();
        //group4();
        //group5();

    }

    /**
     * 使用谓词过滤，如果满足条件返回当前元素，否则返回empty
     */
    private static void group5() {
        Student student = new Student("张三", 22);/*null;*/
        Optional<Student> student1 = Optional.ofNullable(student).filter(s -> {
            return s.getName().length() >= 2;
        });

        System.out.println(student1);
    }

    /**
     * 判断是否存在，如果是则执行消费逻辑
     */
    private static void group4() {
        Student student = new Student("张三", 22);/*null;*/
        System.out.println(Optional.ofNullable(student).isPresent());
        Optional.ofNullable(student).ifPresent(student1 -> {
            System.out.println(student1);
        });
    }

    /**
     * 元素转化
     */
    private static void group3() {
        Student student = new Student("张三", 22);/*null;*/
        //Student student = null;
        Optional<String> optionalS = Optional.ofNullable(student).map((u) -> {
            return u.getName();
        });

        Optional<String> optionalS2 = Optional.ofNullable(student).flatMap((s) -> {
            return s.getName2();
        });

        System.out.println(optionalS2.get());
    }

    /**
     * 第二类操作，如果为空则执行什么操作
     */
    private static void group2() {
        Student student = new Student("张三", 22);/*null;*/
        Student student2 = new Student("lisi", 30);

        Student student1 = Optional.ofNullable(student).orElse(student2);
        System.out.println(student1);

        //Supplier(供应者),对象为空时，直接执行其get方法（我们封装的代码）
        Student student3 = Optional.ofNullable(student).orElseGet(() -> {
            System.out.println("执行---了");
            return new Student("admin", 100);
        });

        System.out.println(student3);


        Student student05 = null;
        try {
            //对象为null，直接抛出自定义异常
            Optional.ofNullable(student05).orElseThrow(() -> {
                return new Exception("对象不存在！");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            System.out.println("执行了---" + name);
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Optional<String> getName2() {
            return Optional.ofNullable(name);
        }
    }

    /**
     * Optional.of/empty/ofNullable分为一组
     */
    private static void group1() {
        Student student = null;
        //为null依然报空指针
        //Optional.of(student);
        //不会报空指针,内部调用empty，会构建一个value为null的Optional对象
        //Optional.ofNullable(student);
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);
    }
}
