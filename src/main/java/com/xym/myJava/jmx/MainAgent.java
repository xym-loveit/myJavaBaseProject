package com.xym.myJava.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-14 15:16
 */
public class MainAgent {
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //每个JMX MBean都必须有一个对象名。对象名是JMX类ObjectName 的一个实例，
        // 必须遵守定义在JMX规范中的语法。也就是说，对象名必须包含一个domain以及一个"键-值"属性列表。
        // 由Main定义的这个对象名中，domain是com.example (包含这个MBean例子的包名)。
        // 此外，"键-值"属性 声明了该对象的类型是为Hello
        ObjectName name = new ObjectName("com.xym.myJava.jmx:type=Hello");
        Hello mbean = new Hello();
        mbs.registerMBean(mbean, name);
        System.out.println("Waiting forever...");
        System.in.read();
    }
}
