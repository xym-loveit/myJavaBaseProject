package com.xym.myJava.head_first._018;

/**
 * 方向解释：终结符表达式
 *
 * @author xym
 * @create 2019-04-24 15:16
 */
public class DirectionNode extends AbstractNode {
    private String direction;

    public DirectionNode(String direction) {
        this.direction = direction;
    }

    //方向表达式的解释操作
    @Override
    public String interpret() {
        if (direction.equalsIgnoreCase("up")) {
            return "向上";
        } else if (direction.equalsIgnoreCase("down")) {
            return "向下";
        } else if (direction.equalsIgnoreCase("left")) {
            return "向左";
        } else if (direction.equalsIgnoreCase("right")) {
            return "向右";
        } else {
            return "无效指令";
        }
    }

    @Override
    public String toString() {
        return "DirectionNode{" +
                "direction='" + direction + '\'' +
                '}';
    }
}
