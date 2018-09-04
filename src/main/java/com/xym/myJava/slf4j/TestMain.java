package com.xym.myJava.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-08-14 10:27
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(TestMain.class);
        String name = "xym";
        logger.debug("Hello {}", name);
        logger.info("Hello \\{} {}", name);

        for (System.out.println("init"); true; System.out.println("3")) {
            System.out.println("exec!!!");
            Thread.sleep(1000*2);
        }

    }
}
