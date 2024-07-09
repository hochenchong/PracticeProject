package behavioral.observer;

/**
 * 战队成员类：具体观察者类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class Player implements Observer {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void help() {
        System.out.println("坚持住，" + this.name + " 来救你！");
    }

    @Override
    public void beAttacked(AllyControlCenter allyControlCenter) {
        System.out.println(this.name + " 被攻击！");
        allyControlCenter.notifyObserver(name);
    }
}
