package com.xym.myJava;

import java.util.Calendar;
import java.util.HashMap;

public class TT {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        System.out.println(cal.getTime());

        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("1", null);
    }
}
