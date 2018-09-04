package com.xym.myJava;

import com.xym.myJava.rpc.RpcConsumer;
import com.xym.myJava.service.BatterCakeService;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 17:05
 */
public class RpcTest {
    public static void main(String[] args) {
        BatterCakeService batterCakeService= RpcConsumer.getService(BatterCakeService.class, "127.0.0.1", 20006);
        String result=batterCakeService.sellBatterCake("双蛋");
        System.out.println(result);
    }
}
