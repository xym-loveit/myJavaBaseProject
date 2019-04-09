package com.xym.myJava.head_first._02;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题实现类----被观察者
 *
 * @author xym
 * @create 2019-04-09 9:47
 */
public class WeatherData implements Subject {

    private List<Observer> observerList;
    //温度
    private float temperature;
    //湿度
    private float humidity;
    //气压
    private float pressure;

    /**
     * 创建对象时，实例化观察者列表
     */
    public WeatherData() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null) {
            this.observerList.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = -1;
        if (observer != null) {
            if ((index = this.observerList.indexOf(observer)) > 0) {
                this.observerList.remove(index);
            }
        }
    }

    /**
     * 当气象站得到更新观测值时，我们通知观察者
     */
    public void measurementsChanged() {
        notifyObserver();
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

    @Override
    public void notifyObserver() {
        for (Observer observer : this.observerList) {
            //一旦发生变化通知给各个观察者
            observer.update(temperature, humidity, pressure);
        }
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
