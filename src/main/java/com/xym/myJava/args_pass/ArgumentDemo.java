package com.xym.myJava.args_pass;

/**
 * 此例用来说明java中只有值传递，没有引用传递的概念
 *
 * @author xym
 * @create 2019-02-18 11:16
 */
public class ArgumentDemo {
    public static void main(String[] args) {
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        new ArgumentDemo().swap(s1, s2);
        System.out.println("2、交换之后观察，原对象 x=" + s1.getName() + " y=" + s2.getName());

        /**
         * 1、交换之后 x=小李 y=小张
         * 2、交换之后观察，原对象 x=小张 y=小李
         */
    }

    /**
     * 注意这里的交换，仅仅是交换了引用的副本，对原对象不会产生影响
     * <p>
     * 方法并没有改变存储在变量 s1 和 s2 中的对象引用。
     * swap方法的参数x和y被初始化为两个对象引用的拷贝，这个方法交换的是这两个拷贝
     *
     * @param x
     * @param y
     */
    private void swap(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("1、交换之后 x=" + x.getName() + " y=" + y.getName());
    }

    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


