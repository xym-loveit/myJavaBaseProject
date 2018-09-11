package com.xym.myJava.observer.v5.jdk;

import com.xym.myJava.observer.v5.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-11 15:19
 */
public class Library extends Observable {
    private List<Book> bookList;

    public Library() {
        bookList = new ArrayList<>();
        //添加两本书
        Book android = new Book("Android","李江东");
        Book HongLou = new Book("红楼梦", "曹雪芹");

        this.bookList.add(android);
        this.bookList.add(HongLou);
    }

    public void addBook(Book book){
        super.setChanged();
        this.bookList.add(book);
        super.notifyObservers(book);
    }

    public void delBook(Book book){
        this.bookList.remove(book);
    }
}
