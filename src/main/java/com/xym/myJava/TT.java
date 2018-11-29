package com.xym.myJava;

import java.nio.channels.SelectionKey;

public class TT {

    static {
        i = 20;
        System.out.println("------------------static block!");
    }

    static int i = 10;

    static {
        i = 30;
    }

    static {
        System.out.println("------------------static block!" + i);
    }


    {
        System.out.println("------------------none static block!");
    }

    public static void main(String[] args) {
        System.out.println(SelectionKey.OP_CONNECT);
        System.out.println(SelectionKey.OP_ACCEPT);
        System.out.println(SelectionKey.OP_READ);
        System.out.println(SelectionKey.OP_WRITE);
        //System.out.println(TT.class);
        /*Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        System.out.println(cal.getTime());

        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("1", null);*/
    }
}
