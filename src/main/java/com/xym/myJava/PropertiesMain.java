package com.xym.myJava;

import java.util.Date;
import java.util.Properties;

/**
 * @author xym
 */
public class PropertiesMain {

    /**
     * <pre>System.getProperties()</pre>
     *
     * @see String
     */
    private String name;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date());
        Properties properties = System.getProperties();
        properties.list(System.out);

        System.out.println("memory usage:");
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Total memory=" + runtime.totalMemory() + " free memory=" + runtime.freeMemory());

        Thread.sleep(1000 * 5);
    }
}
