package pattern05;

/**
 * 恶魔角色建造器
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class DevilBuilder extends ActorBuilder {
    @Override
    public void buildType() {
        actor.setType("恶魔");
    }

    @Override
    public void buildSex() {
        actor.setSex("妖");
    }

    @Override
    public void buildFace() {
        actor.setFace("丑陋");
    }
}
