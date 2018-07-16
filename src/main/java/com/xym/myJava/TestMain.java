package com.xym.myJava;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class TestMain {
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {

        List<Integer> stores = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            stores.add(i);
        }

        System.out.println(Arrays.toString(stores.toArray()));

        System.out.println("倒序-------------------------");

        ListIterator<Integer> integerListIterator = stores.listIterator(stores.size());
        while (integerListIterator.hasPrevious()) {
            System.out.print(integerListIterator.previous());
        }


//        TimeZone tz = TimeZone.getDefault();
//        System.out.println(tz.getID());
//        System.out.println(System.getProperty("user.timezone"));

        /*Random random = new Random(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println(random.nextInt(10));
        }*/

        /*Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(num);*/

        System.setIn(new ByteArrayInputStream("hello".getBytes("UTF-8")));
        System.setOut(new PrintStream("out.txt"));
        System.setErr(new PrintStream("err.txt"));
        try{
            Scanner in = new Scanner(System.in);
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
        }catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
