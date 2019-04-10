package com.xym.myJava.head_first._04;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-10 10:19
 */
public abstract class Pizza {

    private String name;

    /********************pizza原料*******************/
    //面团
    protected Dough dough;
    //果酱
    protected Sauce sauce;
    //蔬菜
    protected Veggies[] veggies;
    //奶酪
    protected Cheese cheese;
    //香肠
    protected Pepperoni pepperoni;
    //蛤蜊
    protected Clams clams;

    /**
     * 准备
     */
   /* protected void prepare() {
        System.out.println("准备");
    }*/

    /**
     * 声明为抽象方法，这些准备原料的工作当然需要原料工厂提供
     */
    abstract void prepare();

    /**
     * 烘烤
     */
    protected void bake() {
        System.out.println("烘烤");
    }

    /**
     * 切片
     */
    protected void cut() {
        System.out.println("切片");
    }

    /**
     * 装盒
     */
    protected void box() {
        System.out.println("装盒");
    }


    /**
     * pizza名字
     */
    public String getName() {
        return name;
    }
}
