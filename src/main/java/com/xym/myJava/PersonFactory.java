package com.xym.myJava;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
