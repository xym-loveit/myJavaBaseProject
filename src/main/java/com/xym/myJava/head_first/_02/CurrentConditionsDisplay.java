package com.xym.myJava.head_first._02;

/**
 * 具体的观察者，实现了观察者接口（Observer）和显示接口（DisplayElement）
 *
 * @author xym
 * @create 2019-04-09 10:08
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    /**
     * 保存主题对象，在以后取消注册时会比较方便
     */
    private WeatherData weatherData;
    private float temperature;
    private float humidity;

    /**
     * 构造器需要weatherData，作为注册之用
     *
     * @param weatherData
     */
    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(String.format("data temperature=%s,humidity=%s", this.temperature, this.humidity));
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
