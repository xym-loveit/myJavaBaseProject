package com.xym.myJava.jmx.mbean;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-12 17:01
 */
public class HelloAgent {
    public static void main(String[] args) throws Exception {
        // 创建一个MBeanServer作为MBean容器,设定MBeanServer的域名为'HelloAgent'
        MBeanServer helloAgent = MBeanServerFactory.createMBeanServer("HelloAgent");
        HelloWorld helloWorld = new HelloWorld();
        /**
         * MBeanServer中注册的每一个在MBean组件都有一个唯一的对象名称标识ObjectName，一个ObjectName包含两个部分，
         * 域和属性键值对,格式为 "domain:type=MBeanName ,如“com.jmx.demo:type=Hello",
         * domain和MBeanName可以随意命名
         */
        ObjectName objectName = new ObjectName("HelloAgent:name=helloWorld");
        //注册MBean
        helloAgent.registerMBean(helloWorld, objectName);
        // 创建一个HTML适配器AdaptorServer,用来和JMX代理交互，HTML适配器提供了使用Web客户端侵入JMX代理的方式。
        // AdaptorServer同时作为MBean注册在MBeanServer里
        ObjectName adapterName = new ObjectName("HelloAgent:name=htmlAdapter");
        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
        helloAgent.registerMBean(htmlAdaptorServer, adapterName);
        htmlAdaptorServer.setPort(9900);
        htmlAdaptorServer.start();
        System.out.println("HelloAgent is running");
    }
}
