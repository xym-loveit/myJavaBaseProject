package com.xym.myJava.head_first._010;

import com.xym.myJava.head_first._010.v2.DinerMenuV2;
import com.xym.myJava.head_first._010.v2.PancakeHouseMenuV2;
import com.xym.myJava.head_first._010.v2.WaitressV2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-14 22:37
 */
public class MenuTestDrive {
    public static void main(String[] args) {
        //PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        //DinerMenu dinerMenu = new DinerMenu();
        //Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
        //waitress.printMenu();
        PancakeHouseMenuV2 pancakeHouseMenuV2 = new PancakeHouseMenuV2();
        DinerMenuV2 dinerMenuV2 = new DinerMenuV2();
        WaitressV2 waitressV2 = new WaitressV2(pancakeHouseMenuV2, dinerMenuV2);
        waitressV2.printMenu();
    }
}
