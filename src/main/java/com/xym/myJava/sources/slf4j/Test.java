package com.xym.myJava.sources.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-26 16:39
 */
public class Test {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Test.class);
        logger.info("hello,{}", new Date());
    }
}
