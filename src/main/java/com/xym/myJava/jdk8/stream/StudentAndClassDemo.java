package com.xym.myJava.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-05 14:49
 */
public class StudentAndClassDemo {

    public static void main(String[] args) {
        List<String> classes = Arrays.asList("高1-1班", "高1-3班", "高1-4班", "高1-8班");
        List<ClassVo> cs = classes.stream().map(ClassVo::new).collect(Collectors.toList());
        Random random = new Random();
        //按班级分组
        Map<String, List<Student>> collect = IntStream.rangeClosed(1, 20).mapToObj(i -> {
            Student student = new Student("name--" + i, cs.get(random.nextInt(cs.size())));
            return student;
        }).collect(Collectors.groupingBy(o -> o.classVo.getName()));
        System.out.println(collect);
    }

    /**
     * 班级类
     */
    static class ClassVo {
        private String name;

        public ClassVo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 学生类
     */
    static class Student {
        private String name;
        private ClassVo classVo;

        public Student(String name, ClassVo classVo) {
            this.name = name;
            this.classVo = classVo;
        }

        public String getName() {
            return name;
        }

        public ClassVo getClassVo() {
            return classVo;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
