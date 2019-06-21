package com.xym.myJava.jdk8.stream;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-20 10:30
 */
public class TestDemo {
    public static void main(String[] args) {
        //flatMap();
        //flatMap2();
        //flatMap3();
        //parallel();
        //optional();
        //reduce();
        //collect1();
        //collect2();
        //toMap1();
        //toMap2();
        //groupingBy();
        //groupingBy2();
        //primitiveStream();
        //parallel3();
    }

    private static void parallel3() {
        //Instant now = Instant.now();
        //try (Stream<String> lines = Files.lines(Paths.get("d:/", "红妆惊梦.txt"))) {
        //    System.out.println(lines.parallel().reduce(0, (sum, str) -> sum + str.length(), (sum1, sum2) -> sum1 + sum2));
        //    System.out.println("const=" + Duration.between(now, Instant.now()).toMillis());
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        new Thread(() -> {
            Instant now2 = Instant.now();
            try (Stream<String> lines = Files.lines(Paths.get("d:/", "红妆惊梦.txt"))) {
                System.out.println(lines.reduce(0, (sum, str) -> sum + str.length(), (sum1, sum2) -> sum1 + sum2));
                System.out.println("const=" + Duration.between(now2, Instant.now()).toMillis());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 原始类型流
     */
    private static void primitiveStream() {
        //rangeClosed包含上界10
        int[] ints = IntStream.rangeClosed(1, 10).toArray();
        System.out.println(Arrays.toString(ints));
        //和包装类型相比没有get方法
        System.out.println(IntStream.rangeClosed(1, 10).max().getAsInt());
        System.out.println(IntStream.rangeClosed(1, 10).sum());
        System.out.println(IntStream.rangeClosed(1, 10).average().getAsDouble());
        System.out.println(IntStream.rangeClosed(1, 10).min().getAsInt());
        System.out.println(IntStream.rangeClosed(1, 10).summaryStatistics());
        //原始类转为包装类型
        Stream<Integer> boxed = IntStream.rangeClosed(1, 10).boxed();
        boxed.forEachOrdered(System.out::println);
        //Random里面可以随机产生数字类型的stream对象
        new Random().ints().parallel().limit(10).forEach(System.out::println);
        new Random().longs().parallel().limit(10).forEach(System.out::println);
        new Random().doubles().parallel().limit(10).forEach(System.out::println);
    }

    private static void groupingBy2() {
        List<Person> data = new ArrayList<>();
        Stream.generate(() -> {
            Random random = new Random();
            return random.nextInt(5);
        }).limit(20).forEach((i) -> {
            Random random = new Random();
            int anInt = random.nextInt(20);
            Person e = new Person("name-" + anInt, String.valueOf(i));
            e.setSalary(anInt * 150);
            data.add(e);
        });
        /**
         * 相当于sql中 select xx,count(xx) as ct from table group by xx,按id分组
         * 得到分组后的每一组有多少人
         */
        System.out.println(data.stream().collect(Collectors.groupingBy(Person::getId, Collectors.counting())));
        /**
         * 按id分组后，计算每一组的所有人薪水之和
         */
        System.out.println(data.stream().collect(Collectors.groupingBy(Person::getId, Collectors.summingInt(Person::getSalary))));
        System.out.println(data);
        /**
         * 按id分组后，获取每组中薪水最高的那位
         */
        Map<String, Optional<Person>> collect = data.stream().collect(Collectors.groupingBy(Person::getId, Collectors.maxBy(Comparator.comparing(Person::getSalary))));
        System.out.println(collect);
    }

    /**
     * 分组
     */
    private static void groupingBy() {
        List<Person> data = new ArrayList<>();
        Stream.generate(() -> {
            Random random = new Random();
            return random.nextInt(20);
        }).limit(20).forEach((i) -> {
            Random random = new Random();
            int anInt = random.nextInt(20);
            data.add(new Person("name-" + anInt, String.valueOf(i)));
        });
        System.out.println(data);
        //Collectors.groupingBy(Person::getId 通过id进行冲突分组Map<String, List<Person>>
        //通过Collectors.mapping(o -> o.getName(), Collectors.toList())实现将Map<String, List<Person>>转为Map<String, List<String>>
        Map<String, List<String>> collect = data.stream().collect(Collectors.groupingBy(Person::getId, Collectors.mapping(o -> o.getName(), Collectors.toList())));
        System.out.println(collect);
        //当分为2组时，可以使用partitioningBy并行优化
        //第一组：id为小于10的数字
        //第二组：id为大于等于10的数字
        Map<Boolean, List<String>> collect1 = data.stream().collect(Collectors.partitioningBy(o -> o.getId().length() >= 2, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(collect1);
    }

    private static void toMap2() {
        //假设现在有这样一批id大量出现冲突的数据，现在想做个分类,把id相同的
        //收集到一个set中
        List<Person> datas = new ArrayList<>();
        Stream.generate(() -> {
            Random random = new Random();
            return random.nextInt(3);
        }).limit(20).forEach((i) -> {
            Random random = new Random();
            int anInt = random.nextInt(50);
            datas.add(new Person("name-" + anInt, String.valueOf(i)));
        });
        System.out.println(datas);
        //按冲突的id为key 取person的name组成的list
        Map<String, List<String>> collect = datas.stream().collect(Collectors.toMap(Person::getId, o -> Collections.singletonList(o.getName()), (o, o2) -> {
            //一旦key 出现冲突时，调用策略
            List<String> list = new ArrayList<>(o);
            list.addAll(o2);
            return list;
            //    TreeMap::new将普通map改为treemap
        }, TreeMap::new));

        System.out.println(collect);
    }

    private static void toMap1() {
        List<Person> datas = new ArrayList<>();
        Stream.generate(() -> {
            Random random = new Random();
            return random.nextInt(5);
        }).limit(10).forEach((i) -> {
            Random random = new Random();
            int anInt = random.nextInt(5);
            datas.add(new Person("name-" + i, String.valueOf(i * anInt)));
        });
        System.out.println(datas);
        //将List<Person>转为依id为key，name为value
        //第三个参数用来处理当key出现冲突时，解决策略，(o（源value）, o2（新value）) -> o返回源value，即为用出现冲突时依原值为主
        Map<String, String> collect = datas.stream().collect(Collectors.toMap(o -> o.getId(), o -> o.getName(), (o, o2) -> o));
        //第三个参数用来处理当key出现冲突时，解决策略，(o（源value）, o2（新value）) -> o2返回新value，即为用出现冲突时依新值为主
        Map<String, String> collect3 = datas.stream().collect(Collectors.toMap(o -> o.getId(), o -> o.getName(), (o, o2) -> o2));
        System.out.println(collect);
        System.out.println(collect3);
        //将List<Person>转为依id为key，Person为value
        Map<String, Person> collect1 = datas.stream().collect(Collectors.toMap(o -> o.getId(), o -> o, (o, o2) -> o));
        System.out.println(collect1);
        Map<String, Person> collect2 = datas.stream().collect(Collectors.toMap(o -> o.getId(), Function.identity(), (o, o2) -> o2));
        System.out.println(collect2);
    }

    private static void collect2() {
        //HashSet::new为每一次的accumulator提供初始化容器，HashSet::add为累加器的动作，HashSet::addAll为合并器的动作
        HashSet<String> collect = Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").collect(HashSet::new, HashSet::add, HashSet::addAll);
        StringBuilder collect2 = Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").collect(StringBuilder::new, (stringBuilder, s) -> {

            stringBuilder.append(s).append("-");
        }, StringBuilder::append);
        System.out.println(String.join("-", collect));
        System.out.println(collect2.toString());
        //将字符串流转为list
        Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").collect(Collectors.toList());
        //使用TreeSet类型
        TreeSet<String> collect1 = Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java", "2", "100").collect(Collectors.toCollection(TreeSet::new));
        System.out.println(String.join("-", collect1));
        System.out.println(Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").collect(Collectors.joining("$")));
        //一个简单的统计概要,统计所有字符串中的长度的最大、最小、平均值
        IntSummaryStatistics collect3 = Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").collect(Collectors.summarizingInt(String::length));
        System.out.println(collect3.getMax() + "--" + collect3.getMin() + "---" + collect3.getAverage() + "--" + collect3.getSum() + "--" + collect3.getCount());
        //parallel并行执行，不能保证输出顺序,但可以提高效率
        Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").parallel().forEach(System.out::print);
        System.out.println();
        //forEachOrdered串行执行，虽然指定并行化执行，但无意义,元素的输出顺序按照流中顺序输出
        Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").parallel().forEachOrdered(System.out::print);
    }

    private static void collect1() {
        //可以将一个stream转为iterator
        Iterator<String> iterator = Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
        Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").iterator().forEachRemaining(System.out::print);

        //注意这里是object数组
        for (Object o : Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").toArray()) {
            System.out.println(o);
        }
        System.out.println();
        //可以把object数组转为String数组，通过构造函数引用String[]::new形式
        for (String s : Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").toArray(String[]::new)) {
            System.out.print(s);
        }

        List<Object> objects = new ArrayList<>();
        IntStream.range(1, 11).forEach(value -> objects.add(value));
        Integer[] integers = objects.toArray(new Integer[10]);
        System.out.println(integers[8]);
    }

    /**
     * 聚合操作
     */
    private static void reduce() {
        //这种聚合操作，被操作元素和结果是同种类型
        Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").reduce((s, s2) -> s + "$" + s2).ifPresent(System.out::println);
        //这种操作，被操作元素和结果是不同类型，如求所有元素的长度,元素类型为String，长度类型为int
        //第一个参数决定最后值的类型，identity为初始种子，第二个参数为accumulator（累加器），第三个参数为combiner（合并器）
        System.out.println(Stream.of("aaa", "bbb", "c3", "dd4", "Hello", "java").parallel().reduce(0, (len, s) -> len + s.length(), Integer::sum));
    }

    /**
     * optional操作
     */
    private static void optional() {
        Person person = new Person();
        Point point = new Point();
        point.setX(1.0);
        point.setY(2.0);
        Address address = new Address();
        //假如这里point为null则下面的过程并不会空指针
        //address.setPoint(point);
        person.setAddress(address);
        Optional.ofNullable(person).flatMap(person1 -> Optional.ofNullable(person1.address)).
                flatMap(address1 -> Optional.ofNullable(address1.point)).
                ifPresent(point1 -> System.out.println(point1.getX() + "-" + point1.getY()));
        Optional.ofNullable(null).orElseGet(String::new);
        System.out.println(Optional.ofNullable(null).isPresent());
    }

    /**
     * 并行执行
     */
    private static void parallel() {
        //parallel并行操作流，找到任意一个条件满足的元素即中断整个操作
        Stream.of("hello", "say", "java", "world", "spring", "zhangsan").parallel().
                filter(s -> s.indexOf("a") > 0).findAny().ifPresent(System.out::println);
        //查找流中是否包含一个元素,并行执行parallel
        System.out.println(Stream.of("hello", "say", "java", "world", "spring", "zhangsan").parallel().anyMatch(s -> s.indexOf("a") > 0));
        //allMatch所有都匹配--parallel
        System.out.println(Stream.of("hello", "say", "java", "world", "spring", "zhangsan").parallel().allMatch(s -> s.length() > 2));
        //noneMatch没有元素匹配--parallel
        System.out.println(Stream.of("hello", "say", "java", "world", "spring", "zhangsan").parallel().noneMatch(s -> s.length() >= 8));
    }

    private static void flatMap3() {
        //有状态的操作
        Stream.of("a", "c", "a", "b", "e", "b", "f", "b").distinct().forEach(System.out::print);
        System.out.println();
        //按单词长度倒序的流
        Stream.of("zhangsan", "hello", "java", "say", "rabbitmq", "activemq").
                sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
    }

    /**
     * 用来展开多级对象（嵌套对象）
     */
    private static void flatMap2() {
        Person person = new Person();
        Point point = new Point();
        point.setX(1.0);
        point.setY(2.0);
        Address address = new Address();
        address.setPoint(point);
        person.setAddress(address);
        Stream.of(person).flatMap(person1 -> Stream.of(person1.getAddress())).
                flatMap(address1 -> Stream.of(address1.getPoint())).
                forEach(point1 -> System.out.println(point1.getX() + "-" + point1.getY()));
    }

    /**
     * 扁平化操作
     */
    private static void flatMap() {
        //如果直接调用map会返回流的嵌套,如果展开需要进行2次循环
        Stream<Stream<Character>> streamStream = Stream.of("Hello", "World").map(TestDemo::characterStream);
        streamStream.forEach(characterStream -> characterStream.forEach(System.out::println));
        //使用flatMap将流平铺展开,一次就可以搞定
        Stream<Character> characterStream = Stream.of("Hello", "World").flatMap(TestDemo::characterStream);
        characterStream.forEach(System.out::println);
    }

    /**
     * 返回一个字符串流
     *
     * @param src
     * @return
     */
    public static Stream<Character> characterStream(String src) {
        ArrayList<Character> strings = new ArrayList<>();
        for (char c : src.toCharArray()) {
            strings.add(c);
        }
        return strings.stream();
    }

    static class Person {
        private String name;
        private String sex;
        private Address address;
        private String id;
        private int salary;

        public Person() {
        }

        public Person(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    static class Address {
        private String address;
        private Point point;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }
    }

    static class Point {
        private double x;
        private double y;

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
