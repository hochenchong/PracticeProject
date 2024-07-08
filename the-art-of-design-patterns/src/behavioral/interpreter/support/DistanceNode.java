package behavioral.interpreter.support;

import behavioral.interpreter.AbstractNode;

/**
 * 距离解释：终结符表达式
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class DistanceNode extends AbstractNode {
    private String distance;

    public DistanceNode(String distance) {
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return this.distance;
    }
}
