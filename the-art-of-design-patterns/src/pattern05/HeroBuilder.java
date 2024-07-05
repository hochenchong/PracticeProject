package pattern05;

/**
 * 英雄角色建造器
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class HeroBuilder extends ActorBuilder {
    @Override
    public void buildType() {
        actor.setType("英雄");
    }

    @Override
    public void buildSex() {
        actor.setSex("男");
    }

    @Override
    public void buildFace() {
        actor.setFace("英俊");
    }
}
