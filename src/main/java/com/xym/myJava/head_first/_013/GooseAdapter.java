package com.xym.myJava.head_first._013;

/**
 * 创建鹅的适配器，将鹅适配成鸭子，就可以使用鸭子的呱呱叫
 * <p>
 * 体现了适配器模式
 *
 * @author xym
 * @create 2019-04-18 15:57
 */
public class GooseAdapter implements Quackable {

    private Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        this.goose.honk();
    }
}
