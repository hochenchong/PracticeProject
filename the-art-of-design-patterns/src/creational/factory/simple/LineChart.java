package creational.factory.simple;

/**
 * 折线图
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class LineChart implements Chart {
    public LineChart() {
        System.out.println("创建折线图");
    }

    @Override
    public void display() {
        System.out.println("显示折线图");
    }
}
