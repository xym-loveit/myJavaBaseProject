package com.xym.myJava.head_first._010.v4;

/**
 * 第四版测试类------------组合模式（分为树干（有孩子节点）和叶子节点（无孩子节点））和迭代器模式结合使用
 *
 * @author xym
 * @create 2019-04-14 22:37
 */
public class MenuTestDriveV4 {
    public static void main(String[] args) {
        MenuComponent pancakeHouseMenu = new Menu("煎饼屋菜单", "煎饼菜单");
        MenuComponent dinerMenu = new Menu("餐厅菜单", "餐厅菜单");
        MenuComponent cafeMenu = new Menu("咖啡菜单", "咖啡不好喝");
        MenuComponent dessertMenu = new Menu("甜心菜单", "甜心很好吃");

        //最顶层菜单
        MenuComponent allMenu = new Menu("All Menus", "combined");
        //菜单加入最顶层菜单
        allMenu.add(pancakeHouseMenu);
        allMenu.add(dinerMenu);
        allMenu.add(cafeMenu);
        //菜单里面加入菜单项
        dinerMenu.add(new MenuItem("餐厅菜单项A", "餐厅菜单项A", 12.5D, false));
        //菜单里面加入另一个菜单
        dinerMenu.add(dessertMenu);
        //在甜品上加入菜单项
        dessertMenu.add(new MenuItem("Apple Pie", "苹果派", 1.59, true));
        //将菜单交由服务员
        WaitressV4 waitressV4 = new WaitressV4(allMenu);
        //服务员将菜单打印
        waitressV4.printMenu();
    }
}
