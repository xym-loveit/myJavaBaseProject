package com.xym.myJava.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-13 15:34
 */
public class LambdaDemo2 {
    public static void main(String[] args) {
        //test001();

        //第1种方法
        Converter<String, Integer> converter = (s) -> Integer.valueOf(s);
        Integer convert = converter.convert("123");
        System.out.println(convert);

        //第2种方法(使用::引用静态方法)
        Converter<String, Integer> converter2 = Integer::valueOf;
        Integer a = converter.convert("123");
        System.out.println(a);

        //第三种方法（使用::引用实例方法）
        SomeThing someThing = new SomeThing();
        Converter<String, String> startWith = someThing::startWith;
        Converter<String, String> endWith = someThing::endWith;
        System.out.println(endWith.convert("Java"));


        //我们通过Person::new来创建一个Person类构造函数的引用。Java编译器会自动地
        //选择合适的构造函数来匹配PersonFactory.create函数的签名，并选择正确的构造
        //函数形式
        PersonFactory<Person> personPersonFactory = Person::new;
        Person person = personPersonFactory.create("Xym", "Loveit");
        System.out.println(person);

    }

    static class SomeThing {
        String startWith(String s) {
            return String.valueOf(s.charAt(0));
        }

        String endWith(String s) {
            return String.valueOf(s.charAt(s.length() - 1));
        }
    }

    /**
     * 创建工厂
     *
     * @param <P>
     */
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
        //P create(String firstName);
    }

    static class Person {
        private String firstName;
        private String lastName;

        public Person() {
        }

        public Person(String firstName) {
            this.firstName = firstName;
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }

        public String getFirstName() {
            return firstName;
        }
    }

    /*注意如果不使用FunctionalInterface注解，也是正确的*/
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    private static void test001() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        System.out.println(names);

        System.out.println("=======");

        //第1种lambda方式
        Collections.sort(names, (String n1, String n2) -> {
            return n2.compareTo(n1);
        });

        System.out.println(names);

        System.out.println("=======");

        //第2种lambda方式（省略了return 和大括号）
        Collections.sort(names, (String n1, String n2) ->
                n2.compareTo(n1)
        );

        System.out.println(names);

        System.out.println("=======");

        //第3种lambda方式（继续省略参数类型）
        Collections.sort(names, (n1, n2) ->
                n2.compareTo(n1)
        );

        System.out.println(names);
    }
}
