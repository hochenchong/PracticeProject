package behavioral.observer;

/**
 * 观察者模式客户端
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class ObserverClient {
    public static void main(String[] args) {
        AllyControlCenter allyControlCenter = new ConcreteAllyControlCenter("666");
        Observer player1 = new Player("张三");
        Observer player2 = new Player("李四");
        Observer player3 = new Player("王五");
        allyControlCenter.join(player1);
        allyControlCenter.join(player2);
        allyControlCenter.join(player3);

        player2.beAttacked(allyControlCenter);
    }
}
