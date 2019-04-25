package com.xym.myJava.head_first._18;

/**
 * 解析机器人指令，简单句子解释：非终结符表达式
 *
 * @author xym
 * @create 2019-04-24 15:14
 */
public class SentenceNode extends AbstractNode {
    //方向
    private AbstractNode direction;
    //动作
    private AbstractNode action;
    //距离
    private AbstractNode distance;

    public SentenceNode(AbstractNode direction, AbstractNode action, AbstractNode distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    //简单句子的解释操作
    @Override
    public String interpret() {
        return direction.interpret() + action.interpret() + distance.interpret();
    }

    @Override
    public String toString() {
        return "SentenceNode{" +
                "direction=" + direction +
                ", action=" + action +
                ", distance=" + distance +
                '}';
    }
}
