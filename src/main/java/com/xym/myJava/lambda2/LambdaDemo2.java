package com.xym.myJava.lambda2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 14:13
 */
public class LambdaDemo2 {
    public static void main(String[] args) {

        Converter<String, Integer> converter = (s) -> Integer.valueOf(s);
        System.out.println(converter.convert("123").getClass());
        //引用静态方法
        Converter<String, Integer> converter2 = Integer::valueOf;
        System.out.println(converter2.convert("111").getClass());

        //普通方法引用
        Something something = new Something();
        Converter<String, String> converter3 = something::startsWith;
        System.out.println(converter3.convert("hello"));

        //构造函数引用
        PersonFactory<Person> factory = Person::new;
        System.out.println(factory.create("san", "zhang"));
        System.out.println(factory.create("wu", "wang"));

        //lambda访问局部变量
        int num = 3;
        Converter<Integer, String> stringConverter =
                (from) -> {
                    System.out.println("------------num=" + num);
                    //在lambda内部也无法修改
                    //num++;
                    return String.valueOf(from + num);
                };
        System.out.println(stringConverter.convert(2));
        //默认已经成为final，无法修改
        //num++;


    }

    static class Lambda4 {
        static int outerStaticNum;
        int outerNum;

        void testScopes() {
            //像匿名对象一样，lambda表达式的内部能获取到对成员变量或静态变量的读写权
            Converter<Integer, String> stringConverter1 = (from) ->
            {
                outerNum = 23;
                return String.valueOf(from);
            };
            Converter<Integer, String> stringConverter2 = (from) ->
            {
                outerStaticNum = 72;
                return String.valueOf(from);
            };
        }
    }


    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }


    @FunctionalInterface
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    static class Person {
        String firstName;
        String lastName;

        Person() {

        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
