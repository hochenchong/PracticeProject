package creational.factory.simple;

/**
 * 图表工厂类
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class ChartFactory {
    // 静态工厂方法
    public static Chart getChart(ChartType type) {
        Chart chart;
        switch (type) {
            case Pie -> chart = new PieChart();
            case LINE -> chart = new LineChart();
            default -> chart = null;
        }
        return chart;
    }
}
