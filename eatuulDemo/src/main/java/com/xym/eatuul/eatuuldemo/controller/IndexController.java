package com.xym.eatuul.eatuuldemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-05 21:14
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "hello!world";
    }
}
