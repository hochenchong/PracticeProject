package creational.factory.simple;

/**
 * 饼状图
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class PieChart implements Chart {
    public PieChart() {
        System.out.println("创建饼状图");
    }

    @Override
    public void display() {
        System.out.println("显示饼状图");
    }
}
