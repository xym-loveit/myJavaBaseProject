package com.xym.myJava.start;

import com.xym.myJava.service.BatterCakeService;
import com.xym.myJava.service.BatterCakeServiceImpl;
import com.xym.myJava.rpc.RpcProvider;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 16:49
 */
public class RpcBootStrap {
    public static void main(String[] args) throws Exception {
        BatterCakeService batterCakeService = new BatterCakeServiceImpl();
        //发布卖煎饼的服务，注册在20006端口
        RpcProvider.export(20006, batterCakeService);
    }
}
