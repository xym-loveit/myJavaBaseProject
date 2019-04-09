package com.xym.myJava.head_first._01._001;

import com.xym.myJava.head_first._01.Duck;
import com.xym.myJava.head_first._01.FlyRocketPowered;
import com.xym.myJava.head_first._01.MallardDuck;
import com.xym.myJava.head_first._01.ModelDuck;

/**
 * 注意当前类的位置是在另一个包里面，此时下面2个成员变量（访问修饰符为protected）无法被引用，即可以有效的
 * 防止滥用现象
 * <p>
 * protected FlyBehavior flyBehavior;
 * protected QuackBehavior quackBehavior;
 *
 * @author xym
 * @create 2019-04-08 16:12
 */
public class DuckSimulator {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performFly();
        duck.performQuack();

        //模型鸭，开始时不会飞行
        Duck model = new ModelDuck();
        model.display();
        model.performFly();
        //升级后动态获取了飞行的能力
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
