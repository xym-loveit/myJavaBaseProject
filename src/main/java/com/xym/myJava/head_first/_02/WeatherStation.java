package com.xym.myJava.head_first._02;

import com.xym.myJava.head_first._02.jdk_inner.CurrentConditionsDisplay2;
import com.xym.myJava.head_first._02.jdk_inner.WeatherData2;

/**
 * 测试类
 *
 * @author xym
 * @create 2019-04-09 10:15
 */
public class WeatherStation {
    public static void main(String[] args) {
        //主题
        WeatherData weatherData = new WeatherData();
        //主题绑定观察者
        CurrentConditionsDisplay conditionsDisplay = new CurrentConditionsDisplay(weatherData);
        //模拟数据发生变动,查看响应结果
        weatherData.setMeasurements(80, 65, 34.6f);
        weatherData.setMeasurements(82, 70, 29.6f);
        weatherData.setMeasurements(79, 90, 29.1f);


        System.out.println("--------------------------------------基于jdk自己的观察者模式----");
        //主题
        WeatherData2 weatherData2 = new WeatherData2();
        //主题绑定观察者
        CurrentConditionsDisplay2 conditionsDisplay2 = new CurrentConditionsDisplay2(weatherData2);
        //模拟数据发生变动,查看响应结果
        weatherData2.setMeasurements(80, 65, 34.6f);
        weatherData2.setMeasurements(82, 70, 29.6f);
        weatherData2.setMeasurements(79, 90, 29.1f);
    }
}
