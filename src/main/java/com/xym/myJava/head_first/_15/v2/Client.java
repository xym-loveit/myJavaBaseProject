package com.xym.myJava.head_first._15.v2;

/**
 * 客户端
 *
 * @author xym
 * @create 2019-04-23 17:01
 */
public class Client {
    public static void main(String[] args) {
        //针对抽象建造者编程
        ActorBuilder ab;
        //反射生成具体建造者对象
        ab = (ActorBuilder) XMLUtil.getBean("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\java\\com\\xym\\myJava\\head_first\\_015\\v2\\config.xml");
        ActorController ac = new ActorController();
        //通过指挥者创建完整的建造者对象
        Actor actor = ac.construct(ab);
        System.out.println(actor.getType() + "的外观：");
        System.out.println("性别：" + actor.getSex());
        System.out.println("面容：" + actor.getFace());
        System.out.println("服饰：" + actor.getCostume());
        System.out.println("发型：" + actor.getHairstyle());
    }
}
