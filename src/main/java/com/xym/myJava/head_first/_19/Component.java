package com.xym.myJava.head_first._19;

/**
 * 抽象同事类
 *
 * @author xym
 * @create 2019-04-25 10:47
 */
public abstract class Component {
    //维持一个中介者的引用，通过中介者和其他同事对象交互
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    //依赖中介者的方法
    public void changed() {
        mediator.componentChanged(this);
    }

    /**
     * 子类需要自己处理的方法
     */
    public abstract void update();

}
