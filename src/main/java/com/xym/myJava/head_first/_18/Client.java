package com.xym.myJava.head_first._18;

/**
 * 客户端测试类
 *
 * @author xym
 * @create 2019-04-24 16:34
 */
public class Client {
    public static void main(String[] args) {
        String instruction = "up move 5 and down run 10 and left move 5";
        InstructionHandler handler = new InstructionHandler();
        handler.handle(instruction);
        String output = handler.output();
        System.out.println(output);
    }
}
