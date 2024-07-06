package pattern16;

/**
 * 享元模式客户端
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class FlyweightClient {
    public static void main(String[] args) {
        // 获取工厂
        IgoChessmanFactory factory = IgoChessmanFactory.getInstance();

        // 获取 3 颗黑棋子
        IgoChessman b1 = factory.getIgoChessman("b");
        IgoChessman b2 = factory.getIgoChessman("b");
        IgoChessman b3 = factory.getIgoChessman("b");
        System.out.println("判断两颗黑棋子是否相同：" + (b1 == b2));
        System.out.println("判断两颗黑棋子是否相同：" + (b2 == b3));

        // 获取 2 颗白棋子
        IgoChessman w1 = factory.getIgoChessman("w");
        IgoChessman w2 = factory.getIgoChessman("w");
        System.out.println("判断两颗白棋子是否相同：" + (w1 == w2));

        // 显示棋子
        b1.display(new Coordinates(1, 2));
        b2.display(new Coordinates(3, 4));
        b3.display(new Coordinates(1, 3));
        w1.display(new Coordinates(2, 5));
        w1.display(new Coordinates(2, 4));
    }
}
