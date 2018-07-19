package com.xym.myJava.cache;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-19 11:19
 */
public class Reference {
    //1M
    private final byte[] data = new byte[2 << 19];

    @Override
    protected void finalize() throws Throwable {
        System.out.println("the reference will be GCs.");
    }

    public static void main(String[] args) {
        System.out.println(new Reference().data.length);
    }
}
