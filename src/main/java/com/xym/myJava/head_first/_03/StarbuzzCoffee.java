package com.xym.myJava.head_first._03;

/**
 * 装置者模式测试类
 * <p>
 * 饮料为被装饰者------
 * <p>
 * 调料为装饰者--------很多调料可以可劲加
 *
 * @author xym
 * @create 2019-04-09 16:44
 */
public class StarbuzzCoffee {
    public static void main(String[] args) {
        //订一杯咖啡，不需要调料
        System.out.println("第一杯咖啡");
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + "," + espresso.cost());
        System.out.println("第二杯咖啡");
        //制造出一个Beverage对象
        Beverage beverage = new DarkRoast();
        //用第一个Mocha装饰
        beverage = new Mocha(beverage);
        //用第二个Mocha装饰
        beverage = new Mocha(beverage);
        //用Whip装饰它
        beverage = new Whip(beverage);
        System.out.println(beverage.getDescription() + "," + beverage.cost());
        System.out.println("第三杯咖啡");
        //混合咖啡
        Beverage houseBlend = new HouseBlend();
        //豆浆装饰
        houseBlend = new Soy(houseBlend);
        //摩卡装饰
        houseBlend = new Mocha(houseBlend);
        //奶泡装饰
        houseBlend = new Whip(houseBlend);
        System.out.println(houseBlend.getDescription() + "," + houseBlend.cost());

    }
}
