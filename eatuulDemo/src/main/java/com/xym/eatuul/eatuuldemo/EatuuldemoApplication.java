package com.xym.eatuul.eatuuldemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author xym
 */
@SpringBootApplication
public class EatuuldemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EatuuldemoApplication.class).properties("server.port=9090").run(args);
    }
}
