package com.xym.myJava.guava;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

/**
 * 断言一般用于过滤
 *
 * @author xym
 * @create 2018-09-19 16:05
 */
public class PredicateDemo {
    public static void main(String[] args) {
        List<String> arrayList = Lists.newArrayList("moom", "dad", "refer", "yes");
        Collection<String> filter = Collections2.filter(arrayList, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return new StringBuilder(input).reverse().toString().equals(input);
            }
        });

        System.out.println(filter);

        //Preconditions.checkNotNull(null,"不能为空");
        //Preconditions.checkArgument(!(arrayList.size()<8),"集合太小了");

        System.out.println(Optional.fromNullable("123").or("null2"));
    }
}
