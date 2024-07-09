package creational.builder;

/**
 * 角色创建控制器：指挥者
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class ActorController {
    // 逐步构建复杂产品对象
    // 也可以将此方法挪到 ActorBuilder 中
    public Actor construct(ActorBuilder actorBuilder) {
        actorBuilder.buildType();
        actorBuilder.buildSex();
        actorBuilder.buildFace();
        return actorBuilder.createActor();
    }
}
