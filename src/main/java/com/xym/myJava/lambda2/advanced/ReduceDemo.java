package com.xym.myJava.lambda2.advanced;

import java.util.Arrays;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 17:48
 */
public class ReduceDemo {
    public static void main(String[] args) {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));


        //求年龄最大的人
        persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent((p) ->
                System.out.println(p.name
                        + "--" + p.age)
        );

        Person identity = new Person("", 0);
        //将多个age和name合并
        Person reduce = persons.stream().reduce(identity, (p1, p2) -> {
            p1.age += p2.age;
            p1.name += p2.name;
            return p1;
        });
        System.out.println("identity=" + identity);
        System.out.println("reduce=" + reduce);
        System.out.println(String.format("name=%s,age=%s", reduce.name, reduce.age));


        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 +
                        sum2);
        System.out.println(ageSum);
    }
}
