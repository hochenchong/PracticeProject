package behavioral.observer;

/**
 * 具体战队控制中心类：具体目标类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class ConcreteAllyControlCenter extends AllyControlCenter {

    public ConcreteAllyControlCenter(String allyName) {
        System.out.println(allyName + " 战队组建成功！");
        System.out.println("----");
        this.allyName = allyName;
    }

    @Override
    public void notifyObserver(String name) {
        System.out.println(this.allyName + " 战队紧急通知，盟友 " + name + " 遭受敌人攻击！");
        for (Observer player : players) {
            if (!player.getName().equalsIgnoreCase(name)) {
                player.help();
            }
        }
    }
}
