package com.xym.myJava.jdk8.lambda;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 一个有意思的构造函数
 *
 * @author xym
 * @create 2019-07-05 13:52
 */
public class ConstructorFunDemo {
    public static void main(String[] args) {
        Apple apple = (Apple) giveMeFruit("apple", 2);
        System.out.println(apple);
    }

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        //构造函数引用
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    /**
     * 根据参数创建水果，很有趣
     *
     * @param fruit
     * @param weight
     * @return
     */
    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }

    interface Fruit {

    }

    static class Apple implements Fruit {
        private String color;
        private double weigth;

        public Apple(String color) {
            this.color = color;
        }

        public Apple(double weigth) {
            this.weigth = weigth;
        }

        public Apple(String color, double weigth) {
            this.color = color;
            this.weigth = weigth;
        }

        public String getColor() {
            return color;
        }

        public double getWeigth() {
            return weigth;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weigth=" + weigth +
                    '}';
        }
    }

    static class Orange implements Fruit {
        private String color;
        private double weight;

        public Orange(double weight) {
            this.weight = weight;
        }

        public Orange(String color) {
            this.color = color;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }
}
