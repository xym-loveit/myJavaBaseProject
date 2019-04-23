package com.xym.myJava.head_first._015.v2;


/**
 * 角色创建控制器--指挥者
 *
 * @author xym
 * @create 2019-04-23 16:28
 */
public class ActorController {

    /**
     * 逐步构建复杂产品对象
     *
     * @param builder
     * @return
     */
    public Actor construct(ActorBuilder builder) {
        Actor actor = null;
        builder.buildType();
        builder.buildSex();
        builder.buildFace();
        builder.buildCostume();
        builder.buildHairstyle();
        actor = builder.createActor();
        return actor;
    }
}
