package com.xym.myJava.head_first._010.v3;

import com.xym.myJava.head_first._010.MenuItem;

import java.util.Iterator;

/**
 * 服务员的第三个版本
 *
 * @author xym
 * @create 2019-04-14 22:32
 */
public class WaitressV3 {

    /**
     * 服务员只依赖接口不依赖具体的抽象类s
     */
    private Menu pancakeHouseMenu;
    private Menu dinerMenu;

    public WaitressV3(Menu pancakeHouseMenu, Menu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        Iterator iterator = this.pancakeHouseMenu.createIterator();
        printMenu(iterator);
        System.out.println("-----------------------------------------");
        iterator = this.dinerMenu.createIterator();
        printMenu(iterator);
    }

    public void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem item = (MenuItem) iterator.next();
            System.out.print(item.getName() + " ");
            System.out.print(item.getPrice() + " ");
            System.out.println(item.getDesc() + " ");
        }
    }
}
