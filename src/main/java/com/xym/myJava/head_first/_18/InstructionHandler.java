package com.xym.myJava.head_first._18;

import java.util.Stack;

/**
 * 指令处理类：工具类
 * <p>
 * 工具类InstructionHandler用于对输入指令进行处理，将输入指令分割为字符串数组，将第1个、第2个和第3个单词组合成一个句子，
 * 并存入栈中；如果发现有单词“and”，则将“and”后的第1个、第2个和第3个单词组合成一个新的句子作为“and”的右表达式，
 * 并从栈中取出原先所存句子作为左表达式，然后组合成一个And节点存入栈中。依此类推，直到整个指令解析结束
 *
 * @author xym
 * @create 2019-04-24 15:28
 */
public class InstructionHandler {
    private String instruction;
    private AbstractNode node;

    /**
     * 指令处理方法
     *
     * @param instruction
     */
    public void handle(String instruction) {
        AbstractNode left = null, right = null;
        AbstractNode direction = null, action = null, distance = null;
        //声明一个栈对象用于存储抽象语法树
        Stack stack = new Stack();
        //以空格分隔指令字符串
        String[] words = instruction.split(" ");
        for (int i = 0; i < words.length; i++) {
            //弹出栈顶表达式作为左表达式
            if (words[i].equalsIgnoreCase("and")) {
                left = (AbstractNode) stack.pop();
                String word1 = words[++i];
                direction = new DirectionNode(word1);
                String word2 = words[++i];
                action = new ActionNode(word2);
                String word3 = words[++i];
                distance = new DistanceNode(word3);
                //右表达式
                right = new SentenceNode(direction, action, distance);
                //将新表达式压入栈中
                stack.push(new AndNode(left, right));
                //如果是从头开始进行解释，则将前三个单词组成一个简单句子SentenceNode并将该句子压入栈中
            } else {
                String word1 = words[i];
                direction = new DirectionNode(word1);
                String word2 = words[++i];
                action = new ActionNode(word2);
                String word3 = words[++i];
                distance = new DistanceNode(word3);
                left = new SentenceNode(direction, action, distance);
                //将新表达式压入栈中
                stack.push(left);
            }
        }
        //将全部表达式从栈中弹出
        this.node = (AbstractNode) stack.pop();
    }

    public String output() {
        //解释表达式
        System.out.println(node);
        String result = node.interpret();
        return result;
    }
}
