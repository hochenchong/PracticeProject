package structural.flyweight;

/**
 * 围棋棋子类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public abstract class IgoChessman {
    public abstract String getColor();

    public void display(Coordinates coordinates) {
        System.out.println("棋子颜色：" + getColor() +
                "，棋子位置：" + coordinates.getX() + "," + coordinates.getY());
    }
}
