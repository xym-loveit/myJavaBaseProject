package com.xym.myJava;

/**
 * @author xym
 */
public class PersonFactoryMain {
    public static void main(String[] args) {
        PersonFactory personFactory = Person::new;
        Person person = personFactory.create("xym", "loveit");
        System.out.println(person);
    }
}
