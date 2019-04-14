package com.xym.myJava.head_first._010.v2;

import com.xym.myJava.head_first._010.MenuItem;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-14 22:32
 */
public class WaitressV2 {

    private PancakeHouseMenuV2 pancakeHouseMenu;
    private DinerMenuV2 dinerMenu;

    public WaitressV2(PancakeHouseMenuV2 pancakeHouseMenu, DinerMenuV2 dinerMenu) {
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
