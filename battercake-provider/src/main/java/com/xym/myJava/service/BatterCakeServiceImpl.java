package com.xym.myJava.service;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 16:47
 */
public class BatterCakeServiceImpl implements BatterCakeService {
    @Override
    public String sellBatterCake(String name) {
        return name + "煎饼,卖的特别好";
    }
}
