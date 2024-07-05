package pattern02.simple;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * 客户端使用
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public class Client {
    public static void main(String[] args) {
        // 从 xml 文件中读取需要使用的图形类型，简单处理，未对 null 做判断
        String type = XMLUtil.getString(XMLTagNameConstant.CHART_TYPE);
        ChartType chartType = ChartType.convertTo(type);
        Chart chart = ChartFactory.getChart(chartType);
        chart.display();
    }
}
