package behavioral.interpreter.support;

import behavioral.interpreter.AbstractNode;

/**
 * 简单橘子解释：非终结符表达式
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class SentenceNode extends AbstractNode {
    private AbstractNode direction;
    private AbstractNode action;
    private AbstractNode distance;

    public SentenceNode(AbstractNode direction, AbstractNode action, AbstractNode distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return direction.interpret() + action.interpret() + distance.interpret();
    }
}
