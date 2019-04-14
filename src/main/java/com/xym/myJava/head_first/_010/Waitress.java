package com.xym.myJava.head_first._010;

import java.util.ArrayList;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-14 22:32
 */
public class Waitress {

    private PancakeHouseMenu pancakeHouseMenu;
    private DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        ArrayList menuItems = this.pancakeHouseMenu.getMenuItems();
        MenuItem[] menuItems1 = this.dinerMenu.getMenuItems();
        //遍历list
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = (MenuItem) menuItems.get(i);
            System.out.print(item.getName() + " ");
            System.out.print(item.getPrice() + " ");
            System.out.println(item.getDesc() + " ");
        }
        System.out.println("-----------------------------------------");
        //遍历数组
        for (int i = 0; i < menuItems1.length; i++) {
            MenuItem item = menuItems1[i];
            System.out.print(item.getName() + " ");
            System.out.print(item.getPrice() + " ");
            System.out.println(item.getDesc() + " ");
        }
    }
}
