package com.xym.myJava.head_first._018;

/**
 * 距离解释：终结符表达式
 *
 * @author xym
 * @create 2019-04-24 15:20
 */
public class DistanceNode extends AbstractNode {

    private String distance;

    public DistanceNode(String distance) {
        this.distance = distance;
    }

    //距离表达式的解释操作
    @Override
    public String interpret() {
        return distance;
    }

    @Override
    public String toString() {
        return "DistanceNode{" +
                "distance='" + distance + '\'' +
                '}';
    }
}
