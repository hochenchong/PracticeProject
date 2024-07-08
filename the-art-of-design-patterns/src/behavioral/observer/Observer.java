package behavioral.observer;

/**
 * 抽象观察类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public interface Observer {
    public String getName();

    public void setName(String name);
    // 支援盟友方法
    public void help();
    // 遭受攻击方法
    public void beAttacked(AllyControlCenter allyControlCenter);
}
