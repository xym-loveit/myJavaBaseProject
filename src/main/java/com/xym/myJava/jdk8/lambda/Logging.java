package com.xym.myJava.jdk8.lambda;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * lambda表达式延迟执行
 *
 * @author xym
 * @create 2019-06-21 11:09
 */
public class Logging {

    public static void info(Logger logger, Supplier<String> message) {
        if (logger.isLoggable(Level.INFO)) {
            /**
             * 表示当前的message并不会立马组装好，只有当日志级别满足时才会
             */
            logger.info(message.get());
        }
    }

    public static void main(String[] args) {
        double x = 3;
        double y = 4;

        info(Logger.getGlobal(), () -> "x: " + x + ", y: " + y);
    }
}
