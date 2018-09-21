package com.xym.myJava.guava;

import com.google.common.primitives.Ints;

import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-19 15:11
 */
public class PrimitivesDemo {
    public static void main(String[] args) {
        //快速完成到集合的转换
        List<Integer> integers = Ints.asList(1, 3, 5, 7, 9);
        String join = Ints.join(",", 1, 2, 3);
        System.out.println(join);
        //原生类型数组快速合并
        int[] concat = Ints.concat(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        for (int i : concat) {
            System.out.print(i + "\t");
        }
        //最大最小so easy
        System.out.println(Ints.max(concat) + "---" + Ints.min(concat));
        //是否包含 so easy
        System.out.println(Ints.contains(concat, 6));
        //集合到数组的转换so easy
        int[] ints = Ints.toArray(integers);
        System.out.println(Ints.join("|", ints));
    }
}
