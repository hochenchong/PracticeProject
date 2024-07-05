package pattern05;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
abstract class ActorBuilder {
    protected Actor actor = new Actor();

    public abstract void buildType();
    public abstract void buildSex();
    public abstract void buildFace();

    public Actor createActor() {
        return actor;
    }
}
