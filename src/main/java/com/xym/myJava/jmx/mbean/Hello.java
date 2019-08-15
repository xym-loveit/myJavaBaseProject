package com.xym.myJava.jmx.mbean;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * 在JMX中，有一个NotificationBroadcasterSupport类：监听通知的产生、通知的发送
 * <p>
 * HelloMBean扩展了NotificationBroadcasterSupport类。NotificationBroadcasterSupport实现了NotificationEmitter接口
 *
 * @author xym
 * @create 2019-08-14 15:14
 */
public class Hello extends NotificationBroadcasterSupport implements HelloMBean {
    @Override
    public void sayHello() {
        System.out.println("hello, world");
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCacheSize() {
        return this.cacheSize;
    }

    @Override
    public synchronized void setCacheSize(int size) {
        int oldSize = this.cacheSize;
        this.cacheSize = size;
        System.out.println("Cache size now " + this.cacheSize);

        //构建通知
        Notification notificatio = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(), "CacheSize changed", "CacheSize", "int", oldSize, this.cacheSize);
        sendNotification(notificatio);
    }

    /**
     * 返回这个MBean将会发送的通知类型信息
     *
     * @return
     */
    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
                description);
        return new MBeanNotificationInfo[]{info};
    }

    private final String name = "Reginald";
    private int cacheSize = DEFAULT_CACHE_SIZE;
    private static final int DEFAULT_CACHE_SIZE = 200;
    private long sequenceNumber = 1;
}
