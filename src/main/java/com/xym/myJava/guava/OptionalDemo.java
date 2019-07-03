package com.xym.myJava.guava;


import com.google.common.base.Optional;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-03 14:16
 */
public class OptionalDemo {
    public static void main(String[] args) {
        System.out.println(Optional.absent().isPresent());
        //System.out.println(Optional.fromNullable(null).isPresent());
        //System.out.println(Optional.of(null));
        //System.out.println(Optional.absent().get());
        //System.out.println(Optional.absent().or("def"));
        //System.out.println(Optional.absent().orNull());
        //System.out.println(Optional.absent().asSet());

    }
}
