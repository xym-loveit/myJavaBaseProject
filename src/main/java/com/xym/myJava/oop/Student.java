package com.xym.myJava.oop;

/**
 * 输出结果为：
 * Student say...0
 * Student say...10
 * <p>
 * 第一个0是在子类执行new的时候调用了父类的无参构造函数，父类调用了say方法，但此时say方法被子类重写了，但由于子类并未实例化，所以未初始化的a现在值为0
 *
 * @author xym
 */
public class Student extends Person {

    private int a = 10;

    public Student() {

    }

    @Override
    public void say() {
        System.out.println("Student say..." + a);
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.say();
    }
}
