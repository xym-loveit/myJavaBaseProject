package com.xym.myJava.head_first._02;

/**
 * 观察者--发现主题数据变化，需要作出各自操作
 *
 * @author xym
 * @create 2019-04-09 9:48
 */
public interface Observer {
    /**
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 压力
     */
    void update(float temp, float humidity, float pressure);
}
