package com.xym.myJava.head_first._10;

/**
 * 菜单项
 *
 * @author xym
 * @create 2019-04-14 22:18
 */
public class MenuItem {
    /**
     * 菜名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 是否素食
     */
    private boolean vegetarian;
    /**
     * 价格
     */
    private double price;

    public MenuItem(String name, String desc, boolean vegetarian, double price) {
        this.name = name;
        this.desc = desc;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
