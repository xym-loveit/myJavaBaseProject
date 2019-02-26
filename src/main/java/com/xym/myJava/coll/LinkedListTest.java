package com.xym.myJava.coll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-21 16:58
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        System.out.println(strings);

        /************************queue操作***************************/
        //获取不移除
        System.out.println(strings.peek());
        System.out.println(strings.element());
        System.out.println(strings.getFirst());
        //获取并移除
        System.out.println(strings.poll() + " " + strings);
        strings.remove();
        System.out.println(strings);
        strings.offer("6");
        System.out.println(strings);

        /******************************deque操作***********************************/
        strings.offerFirst("100");//在列表开头查询指定元素
        System.out.println(strings);
        strings.offerLast("101");
        System.out.println(strings);//在此列表末尾插入指定的元素
        // 获取但不移除此列表的第一个元素-- 获取但不移除此列表的最后一个元素
        System.out.println(strings.peekFirst() + "--" + strings.peekLast());
        //获取并移除此列表的第一个元素
        strings.pollFirst();
        //获取并移除此列表的最后一个元素
        strings.pollLast();
        System.out.println(strings);
        //将元素推入此列表所表示的堆栈（插入到列表的头）
        strings.push("1000");
        System.out.println(strings);
        strings.pop();// 从此列表所表示的堆栈处弹出一个元素（获取并移除列表第一个元素）
        System.out.println("After pop():" + strings);
        strings.add("3");
        System.out.println("After add()" + strings);
        //strings.removeFirstOccurrence("3");//从此列表中移除第一次出现的指定元素（从头部到尾部遍历列表）
        strings.removeLastOccurrence("3"); // 从此列表中移除最后一次出现的指定元素（反向从尾部到头部遍历列表）
        //System.out.println("After removeFirstOccurrence()" + strings);
        //System.out.println("After removeFirstOccurrence()" + strings);
        System.out.println("After removeLastOccurrence()" + strings);

        LinkedList<Integer> linkedList = new LinkedList<>();
        /************************** 遍历操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.clear();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        // 迭代器遍历
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long end = System.currentTimeMillis();
        System.out.println("Iterator：" + (end - start) + " ms");

        // 顺序遍历(随机遍历)
        start = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("for：" + (end - start) + " ms");

        // 另一种for循环遍历
        start = System.currentTimeMillis();
        for (Integer i : linkedList) {
            i++;
        }
        end = System.currentTimeMillis();
        System.out.println("for2：" + (end - start) + " ms");

        // 通过pollFirst()或pollLast()来遍历LinkedList
        LinkedList<Integer> temp1 = new LinkedList<>();
        temp1.addAll(linkedList);
        start = System.currentTimeMillis();
        while (temp1.size() != 0) {
            temp1.pollFirst();
        }
        end = System.currentTimeMillis();
        System.out.println("pollFirst()或pollLast()：" + (end - start) + " ms");

        // 通过removeFirst()或removeLast()来遍历LinkedList
        LinkedList<Integer> temp2 = new LinkedList<>();
        temp2.addAll(linkedList);
        start = System.currentTimeMillis();
        while (temp2.size() != 0) {
            temp2.removeFirst();
        }
        end = System.currentTimeMillis();
        System.out.println("removeFirst()或removeLast()：" + (end - start) + " ms");


        ArrayList<Integer> arrayList = new ArrayList<>();
        /************************** 遍历操作 ************************/
        System.out.println("-----------------------------------------");
        arrayList.clear();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        // 迭代器遍历
        start = System.currentTimeMillis();
        Iterator<Integer> iterator2 = arrayList.iterator();
        while (iterator2.hasNext()) {
            iterator2.next();
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList Iterator：" + (end - start) + " ms");

        // 顺序遍历(随机遍历)
        start = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList for：" + (end - start) + " ms");

        // 另一种for循环遍历
        start = System.currentTimeMillis();
        for (Integer i : arrayList) {
            i++;
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList for2：" + (end - start) + " ms");
    }


}
