package com.xym.myJava.head_first._18;

/**
 * And解释：非终结符表达式
 *
 * @author xym
 * @create 2019-04-24 15:12
 */
public class AndNode extends AbstractNode {
    //and左表达式
    private AbstractNode left;
    //and右表达式
    private AbstractNode right;

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    //And表达式解释操作
    @Override
    public String interpret() {
        return left.interpret() + "再" + right.interpret();
    }

    @Override
    public String toString() {
        return "AndNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
