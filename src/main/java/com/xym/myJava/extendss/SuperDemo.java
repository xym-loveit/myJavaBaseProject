package com.xym.myJava.extendss;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-19 11:00
 */
public class SuperDemo {
    public static void main(String[] args) {
        SuperDemo superDemo = new SuperDemo();
        superDemo.new Sub().bar();
    }

    class Super {
        protected int number;

        protected void showNumber() {
            System.out.println("number=" + number);
        }
    }

    class Sub extends Super {
        void bar() {
            super.number = 10;
            super.showNumber();
        }
    }
}
