package com.xym.myJava.args_pass;

import java.util.Arrays;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-21 15:10
 */
public class ArrayCopyTest {
    static String[] array1 = new String[]{"a", "b", "c", "d"};

    public static void main(String[] args) {
        //arraylist 扩容为原数组的1.5倍
        array1 = Arrays.copyOf(array1, array1.length + (array1.length >> 1));
        System.out.println(Arrays.toString(array1));

        String str = "{\"default\":{\"penaltyAmountRate\":0.25,\"penaltyAmountArray\":[{\"originalAmount\":1050,\"penaltyAmount\":450},{\"originalAmount\":2000,\"penaltyAmount\":857}]},\"credit700\":{\"penaltyAmountRate\":0.42,\"penaltyAmountArray\":[{\"originalAmount\":1050,\"penaltyAmount\":450},{\"originalAmount\":2000,\"penaltyAmount\":857}]}}";

        /**
         * [a, b, c, d, null, null]
         * [a, b, e, c, d, null]
         *
         * 这里看到的结果就是字母 e 后面的元素都往后移动了一个位置
         */
        add(2, "e");

        System.out.println(Arrays.toString(array1));
        String[] ss = (String[]) toArray();
        System.out.println(Arrays.toString(ss));
        ss[0] = "R";
        System.out.println(Arrays.toString(ss));
        System.out.println(Arrays.toString(array1));
    }

    /**
     * arraylist 向指定位置添加元素的精髓
     *
     * @param index
     * @param element
     */
    public static void add(int index, String element) {
        /**
         * arraycopy 参数为：原数组--从原数组哪个位置拷贝--目标数组--拷贝到目标数组中的那个起始位置-拷贝数量
         */
        System.arraycopy(array1, index, array1, index + 1, (array1.length - 2 - index));
        array1[index] = element;
    }

    /**
     * 转换为数组,该方法的返回是安全的，因为返回的数组为一个全新的对象，没有引用泄露
     */
    public static Object[] toArray() {
        /**
         * 这里减一的目的是和arraylist的size属性相当,还有一个null是扩容的，需要去掉
         */
        return Arrays.copyOf(array1, array1.length - 1);
    }
}
