package behavioral.interpreter.support;

import behavioral.interpreter.AbstractNode;

/**
 * 方向解释：终结符表达式
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class DirectionNode extends AbstractNode {
    private String direction;

    public DirectionNode(String direction) {
        this.direction = direction;
    }

    // 方向表达式的解释操作
    @Override
    public String interpret() {
        if ("up".equalsIgnoreCase(direction)) {
            return "向上";
        }
        if ("down".equalsIgnoreCase(direction)) {
            return "向下";
        }
        if ("left".equalsIgnoreCase(direction)) {
            return "向左";
        }
        if ("right".equalsIgnoreCase(direction)) {
            return "向右";
        }
        return "无效指令";
    }
}
