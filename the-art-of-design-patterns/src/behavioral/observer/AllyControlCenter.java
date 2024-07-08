package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 战队控制中心类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public abstract class AllyControlCenter {
    // 战队名称
    protected String allyName;
    protected List<Observer> players = new ArrayList<>();

    public String getAllyName() {
        return allyName;
    }

    public void setAllyName(String allyName) {
        this.allyName = allyName;
    }

    public void join(Observer observer) {
        System.out.println(observer.getName() + " 加入 " + this.allyName + " 战队！");
        players.add(observer);
    }

    public void quit(Observer observer) {
        System.out.println(observer.getName() + " 退出 " + this.allyName + " 战队！");
        players.remove(observer);
    }

    public abstract void notifyObserver(String name);
}
