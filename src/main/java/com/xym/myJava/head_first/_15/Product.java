package com.xym.myJava.head_first._15;

/**
 * 表示复杂产品
 *
 * @author xym
 * @create 2019-04-23 15:33
 */
public class Product {
    /**
     * 复杂产品有很多部分组成
     */
    private String partA;
    /**
     * 复杂产品有很多部分组成
     */
    private String partB;
    /**
     * 复杂产品有很多部分组成
     */
    private String partC;

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    @Override
    public String toString() {
        return "Product{" +
                "partA='" + partA + '\'' +
                ", partB='" + partB + '\'' +
                ", partC='" + partC + '\'' +
                '}';
    }
}
