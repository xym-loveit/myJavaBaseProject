package com.xym.myJava.head_first._010;

import com.xym.myJava.head_first._010.v3.DinerMenuV3;
import com.xym.myJava.head_first._010.v3.Menu;
import com.xym.myJava.head_first._010.v3.PancakeHouseMenuV3;
import com.xym.myJava.head_first._010.v3.WaitressV3;

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
        //PancakeHouseMenuV2 pancakeHouseMenuV2 = new PancakeHouseMenuV2();
        //DinerMenuV2 dinerMenuV2 = new DinerMenuV2();
        //WaitressV2 waitressV2 = new WaitressV2(pancakeHouseMenuV2, dinerMenuV2);
        //waitressV2.printMenu();

        Menu pancakeHouseMenuV3 = new PancakeHouseMenuV3();
        Menu dinerMenuV3 = new DinerMenuV3();
        WaitressV3 waitressV3 = new WaitressV3(pancakeHouseMenuV3, dinerMenuV3);
        waitressV3.printMenu();
    }
}
