package com.xym.myJava.observer.v5.jdk;

import com.xym.myJava.observer.v5.Book;

/**
 * 采用jdk自带的观察者模式实现
 *
 * @author xym
 * @create 2018-09-11 15:24
 */
public class Test {
    public static void main(String[] args) {
        Library library2 = new Library();

        ReaderA readerA2 = new ReaderA();
        ReaderB readerB2 = new ReaderB();

        library2.addObserver(readerA2);
        library2.addObserver(readerB2);

        library2.addBook(new Book("朝花夕拾", "鲁迅"));
    }
}
