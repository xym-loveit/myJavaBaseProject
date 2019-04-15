package com.xym.myJava.head_first._010.v4;

/**
 * 叶节点--菜单项
 *
 * @author xym
 * @create 2019-04-15 17:38
 */
public class MenuItem extends MenuComponent {
    private String name;
    private String desc;
    private double price;
    private boolean vegetarian;

    public MenuItem(String name, String desc, double price, boolean vegetarian) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.vegetarian = vegetarian;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public void print() {
        System.out.print(" " + getName());
        if (isVegetarian()) {
            System.out.print("(v)");
        }
        System.out.println(", " + getPrice());
        System.out.println("     -- " + getDesc());
    }
}
