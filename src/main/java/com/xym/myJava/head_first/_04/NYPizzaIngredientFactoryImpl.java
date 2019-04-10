package com.xym.myJava.head_first._04;

/**
 * pizza原料创建工厂--纽约工厂（抽象工厂模式），其中每一个方法都是工厂方法
 *
 * @author xym
 * @create 2019-04-10 14:44
 */
public class NYPizzaIngredientFactoryImpl implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[0];
    }

    @Override
    public Pepperoni createPepperoin() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
