package com.xym.myJava.head_first._02.jdk_inner;

import java.util.Observable;

/**
 * 基于jdk实现的观察者模式，需要继承Observable类实现主题功能（主题订阅、主题取消订阅、数据更新发布通知）
 *
 * @author xym
 * @create 2019-04-09 10:36
 */
public class WeatherData2 extends Observable {

    //温度
    private float temperature;
    //湿度
    private float humidity;
    //气压
    private float pressure;

    /**
     * 当气象站得到更新观测值时，我们通知观察者
     */
    public void measurementsChanged() {
        //确认更改标识,查看该方法修饰符，会发现为protected，也就是说要想使用必须继承
        setChanged();
        //通知观察者，数据已更新，但此时注意并未传递任何数据，目的是采用客户端拉模式（pull）
        //通过参数不为空，表示采用推模式（push）
        notifyObservers("---data");
    }

    /**
     * 测试方法，模拟数据发生变化
     *
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
