package pattern05;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
public class ActorClient {
    public static void main(String[] args) {
        ActorBuilder actorBuilder = (ActorBuilder) XMLUtil.getBean(XMLTagNameConstant.ACTOR_BUILDER);
        ActorController actorController = new ActorController();
        // 通过指挥官创建完整的建造者对象
        Actor actor = actorController.construct(actorBuilder);
        System.out.println("类型：" + actor.getType());
        System.out.println("性别：" + actor.getSex());
        System.out.println("面容：" + actor.getFace());
    }
}
