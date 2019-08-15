package com.xym.myJava.jmx.mxbeans;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-15 15:18
 */
public class MXBeanMain {
    public static void main(String[] args) throws Exception {
        //得到 platform MBean server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        //为MXBean QueueSampler创建一个对象名
        ObjectName objectName = new ObjectName("com.xym.myJava.jmx.mxbeans:type=QueueSampler");
        //创建一个 Queue 实例，供 QueueSampler MXBean来处理
        Queue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("Request-1");
        queue.add("Request-2");
        queue.add("Request-3");
        //将该Queue实例喂给新创建的 QueueSampler MXBean
        QueueSampler mxbean = new QueueSampler(queue);
        //在MBean Server中注册该MXBean，和注册标准MBean方式完全相同
        mBeanServer.registerMBean(mxbean, objectName);
        System.out.println("Waiting...");
        System.in.read();
    }
}
