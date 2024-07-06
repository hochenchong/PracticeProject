package creational.factory.simple;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 图形类型枚举类
 *
 * @author hochenchong
 * @date 2024/7/5
 */
public enum ChartType {
    Pie("pie", "饼状图"),
    LINE("line", "折线图"),
    ;

    private String type;
    private String desc;

    ChartType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static ChartType convertTo(String type) {
        return Stream.of(ChartType.values()).filter(t -> Objects.equals(type, t.getType())).findAny().orElse(null);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
