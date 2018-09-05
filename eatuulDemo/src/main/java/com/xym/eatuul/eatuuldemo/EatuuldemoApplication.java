package com.xym.eatuul.eatuuldemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 配合服务网关，做测试使用而已
 *
 * @author xym
 */
@SpringBootApplication
public class EatuuldemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EatuuldemoApplication.class).properties("server.port=9090").run(args);
    }
}
