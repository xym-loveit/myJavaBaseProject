package com.xym.myJava.observer.v5;

/**
 * 观察者模式测试
 *
 * @author xym
 * @create 2018-09-11 15:10
 */
public class TestMain {
    public static void main(String[] args) {

        /**
         * 图书馆，被观察者（通知人）
         */
        Library library = new Library();
        /**
         * 创建2个读者（观察者）
         */
        ReaderA readerA = new ReaderA();
        ReaderB readerB = new ReaderB();

        /**
         *2个读者订阅了图书馆消息
         */
        library.addObserver(readerA);
        library.addObserver(readerB);

        /**
         * 图书馆添加了一本书
         */
        library.addBook(new Book("朝花夕拾", "鲁迅"));

    }
}
