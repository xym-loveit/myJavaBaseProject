package com.xym.myJava.guava;

import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.collect.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-03 14:32
 */
public class CommonBaseDemo {
    //private static final Logger logger = LoggerFactory.getLogger(CommonBaseDemo.class);

    public static void main(String[] args) {
        //optional();
        //preconditions();
        //objects();
        //order001();
        //order002();
        //System.out.println(Range.atMost(100).contains(100));
        //throwable();


        //stopWatch1();
        //try {
        //    stopWatch2();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        //multiset();

        //key与value可以互换的一种数据结构
        //bitMap();
    }

    private static void bitMap() {
        BiMap<String, String> upperToSmall = HashBiMap.create();
        upperToSmall.put("A", "a");
        upperToSmall.put("B", "b");
        upperToSmall.put("C", "c");
        //upperToSmall.put("D", "c");
        //System.out.println(upperToSmall.get("A"));
        //强制加入
        upperToSmall.forcePut("D", "c");
        System.out.println(upperToSmall.get("D"));
        System.out.println(upperToSmall.get("C"));
        BiMap<String, String> smallToUpper = upperToSmall.inverse();
        System.out.println(smallToUpper.get("c"));  // D
        //在反转后的map中操作会影响原map
        smallToUpper.put("E", "e");
        System.out.println(upperToSmall);   // {A=a, B=b, D=c, e=E}
        System.out.println(smallToUpper);   // {a=A, b=B, c=D, E=e}
    }

    private static void multiset() {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        System.out.println("Occurrence of 'b' : " + multiset.count("b"));
        System.out.println("Total Size : " + multiset.elementSet().size());
        // 遍历
        Set<String> set = multiset.elementSet();
        System.out.print("Set [");
        for (String s : set) {
            System.out.print(s);
        }
        System.out.println("]");

        // 使用迭代器
        Iterator<String> iterator = multiset.iterator();
        System.out.print("MultiSet [");
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println("]");
        // 不同元素的个数
        System.out.println("MultiSet [");
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("    Element: " + entry.getElement() + ", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");

        // 删除元素
        multiset.remove("b", 2);
        // 查找元素
        System.out.println("Occurence of 'b' : " + multiset.count("b"));
    }

    private static void stopWatch2() throws InterruptedException {
        // 创建stopwatch并开始计时
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1980);

        // 以秒打印从计时开始至现在的所用时间，向下取整
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 停止计时
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 再次计时
        stopwatch.start();
        Thread.sleep(100);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 2

        // 重置并开始
        stopwatch.reset().start();
        Thread.sleep(1030);

        // 检查是否运行
        System.out.println(stopwatch.isRunning()); // true
        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS); // 1034
        System.out.println(millis);

        // 打印
        System.out.println(stopwatch.toString()); // 1.034 s
    }

    private static void stopWatch1() {
        String orderNo = "12345678";

        System.out.println(String.format("订单 %s 开始处理", orderNo));
        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            TimeUnit.SECONDS.sleep(1);  // 1秒处理时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("订单 %s 处理完成，耗时 %s ", orderNo, stopwatch.stop()));
    }

    private static void throwable() {
        try {
            new CommonBaseDemo().showcaseThrowables();
        } catch (InvalidInputException e) {
            System.out.println(Throwables.getRootCause(e));
        }
    }

    public void showcaseThrowables() throws InvalidInputException {
        try {
            sqrt(-3.0);
        } catch (Throwable e) {
            //check the type of exception and throw it
            Throwables.throwIfInstanceOf(e, InvalidInputException.class);
            Throwables.propagate(e);
        }
    }

    public double sqrt(double input) throws InvalidInputException {
        if (input < 0) {
            throw new InvalidInputException();
        }
        return Math.sqrt(input);
    }

    class InvalidInputException extends Exception {
    }

    private static void order002() {
        try {
            //不能为null
            Ordering<Comparable> natural = Ordering.natural();
            natural.compare(1, null);
        } catch (Exception e) {
        }

        List<Integer> list1 = Lists.newArrayList();
        List<Integer> list2 = Lists.newArrayList(1);
        List<Integer> list3 = Lists.newArrayList(1, 1);
        List<Integer> list4 = Lists.newArrayList(1, 2);
        List<Integer> list5 = Lists.newArrayList(1, null, 2);
        List<Integer> list6 = Lists.newArrayList(2);
        Integer nullInt = null;
        List<Integer> list7 = Lists.newArrayList(nullInt);
        List<Integer> list8 = Lists.newArrayList(nullInt, nullInt);
        List<List<Integer>> list = Lists.newArrayList(list1, list2, list3, list4, list5, list6, list7, list8, null);
        //nullsFirst：根据Ordering排序，null值放在最前面，并使用它来比较非空值。
        //natural：对可排序类型做自然排序，如数字按大小，日期按先后排序
        //reverse：返回与当前Ordering相反的排序。相当于 Collections.reverseOrder(Comparator)。
        //lexicographical：返回一个新的Ordering，通过相应的元素两两迭代，直到找到一个非零的结果。强加“字典顺序”。
        Ordering<Iterable<Integer>> example = Ordering.<Integer>natural().nullsFirst().reverse().lexicographical().reverse().nullsLast();
        List<List<Integer>> sorted = example.sortedCopy(list);
        sorted.forEach(System.out::println);
    }

    private static void order001() {
        //允许有null
        Ordering<Object> allEqual = Ordering.allEqual();
        //Ordering<Object> reverse = allEqual.reverse();
        //System.out.println(allEqual.equals(reverse));
        //System.out.println(allEqual.compare(1, 100));
        //System.out.println(allEqual.compare(new Object(), new Object()));
        List<String> strings = ImmutableList.of("b", "a", "d", "c");
        System.out.println(strings);
        //strings.add("h");
        //返回一个可变的集合，包含根据Ordering对传入元素排序后的所有元素。
        System.out.println(allEqual.sortedCopy(strings));
        //返回一个不可变的集合，包含根据Ordering对传入元素排序后的所有元素。
        System.out.println(allEqual.immutableSortedCopy(strings));
    }

    private static void objects() {
        //System.out.println(Objects.equal(null, "aaa"));
        //System.out.println(Objects.equal(null, null));
        //System.out.println(Objects.equal(2, null));
        //System.out.println(Objects.hashCode(null));
        //System.out.println(Objects.hashCode(1));
        //System.out.println(Objects.hashCode(1));
        //System.out.println(Objects.hashCode("A"));
        //System.out.println(Objects.hashCode("a"));
    }

    private static void preconditions() {
        int i1 = 1;
        //Preconditions.checkArgument(i1 > 2, "参数 %s 错误", i1);
        //Preconditions.checkArgument(1>2);
        //Preconditions.checkNotNull(null, "参数不能为null");
        //Preconditions.checkNotNull(null, "参数不能为null %s", "Hello");
        //Preconditions.checkElementIndex(7,8);
        //Preconditions.checkPositionIndex(8, 8);
        //Preconditions.checkState(1 > 2, "error");
    }

    private static void optional() {
        //System.out.println(Optional.absent().isPresent());
        //System.out.println(Optional.fromNullable(null).isPresent());
        //System.out.println(Optional.of(null));
        //System.out.println(Optional.absent().get());
        //System.out.println(Optional.absent().or("def"));
        //System.out.println(Optional.absent().orNull());
        //System.out.println(Optional.absent().asSet());
    }
}
