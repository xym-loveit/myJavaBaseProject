package com.xym.myJava.head_first._02.jdk_inner;

import com.xym.myJava.head_first._02.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * 基于jdk实现的观察者（订阅者，当主题内容发生变化时，会被通知或主动拉取）
 *
 * @author xym
 * @create 2019-04-09 10:41
 */
public class CurrentConditionsDisplay2 implements Observer, DisplayElement {

    private Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay2(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    /**
     * @param obs 当前参数为观察模式中的被观察主题
     * @param arg 被观察主题传递的参数，如果为空表示采用拉模式，反之不为空表示为push模式
     */
    @Override
    public void update(Observable obs, Object arg) {
        System.out.println("当前传递参数 arg=" + arg);
        if (obs instanceof WeatherData2) {
            WeatherData2 weatherData2 = ((WeatherData2) obs);
            this.humidity = weatherData2.getHumidity();
            this.temperature = weatherData2.getTemperature();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println(String.format("data temperature=%s,humidity=%s", this.temperature, this.humidity));
    }
}
