package com.xym.myJava.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-12 13:41
 */
public class Main {
    public static void main(String[] args) {
        //Integer[] as = {1, 2, 3, 4, 5, 6, 7, 8};
        //Integer[] integers = Arrays.copyOf(as, 10, Integer[].class);
        //for (Integer integer : integers) {
        //    System.out.println(integer);
        //}

        Deque<Integer> deque = new ArrayDeque<>(10);
        System.out.println(deque.size());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.add(4);
        System.out.println(deque);

        System.out.println(deque.getFirst());
        System.out.println(deque.peekFirst());
        System.out.println(deque.pop());
        System.out.println(deque.peekFirst());
        System.out.println(deque.getLast());

      /*  int[] ints = new int[8];
        int head = 0;
        int a = 100;
        ints[head = (head - 1) & (ints.length - 1)] = a;
        System.out.println(Arrays.toString(ints));
        System.out.println(head);
        int b = 90;
        ints[head = (head - 1) & (ints.length - 1)] = b;
        System.out.println(Arrays.toString(ints));
        System.out.println(head);*/



    }
}
