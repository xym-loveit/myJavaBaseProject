package com.xym.myJava.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-19 14:45
 */
public class StringDemo {
    //连接器
    private static final Joiner JOINER = Joiner.on(",").skipNulls();
    //分割器
    private static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();
    //数字匹配
    private static final CharMatcher CHAR_MATCHER_DIGIT = CharMatcher.digit();
    //匹配任何字符
    private static final CharMatcher CHAR_MATCHER_ANY = CharMatcher.any();


    public static void main(String[] args) {
        String join = JOINER.join(Lists.newArrayList("a", null, "b"));
        System.out.println("join=" + join);

        for (String s : SPLITTER.split(" a ,    , b ,,")) {
            System.out.println("|" + s + "|");
        }

        //保留匹配的字符，移除其他
        System.out.println(CHAR_MATCHER_DIGIT.retainFrom("abcs2dfe134f~"));
        //移除匹配的字符，保留其他，和上面恰恰相反
        System.out.println(CHAR_MATCHER_DIGIT.removeFrom("abcs2dfe134f~"));
        //匹配到一个范围的字符
        System.out.println(CHAR_MATCHER_ANY.inRange('a', 'f').removeFrom("abcs2dfe134fherop"));
        //or组合多个匹配器，匹配上则通过*替换
        String s = CHAR_MATCHER_ANY.inRange('a', 'f').or(CHAR_MATCHER_ANY.is('n')).replaceFrom("zhonghuarenminggongheguo", "*");
        System.out.println(s);
    }
}
