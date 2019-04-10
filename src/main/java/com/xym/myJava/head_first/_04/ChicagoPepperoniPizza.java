package com.xym.myJava.head_first._04;

/**
 * 纽约风味
 *
 * @author xym
 * @create 2019-04-10 10:40
 */
public class ChicagoPepperoniPizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    /**
     * 要制作pizza需要工厂提供原料，所以每个比萨类都需要从构造函数中得到一个工厂，并把工厂存储在实例变量中
     *
     * @param ingredientFactory
     */
    public ChicagoPepperoniPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + getName());
        this.dough = ingredientFactory.createDough();
        this.sauce = ingredientFactory.createSauce();
        this.cheese = ingredientFactory.createCheese();
    }

    @Override
    public String getName() {
        return "";
    }
}
