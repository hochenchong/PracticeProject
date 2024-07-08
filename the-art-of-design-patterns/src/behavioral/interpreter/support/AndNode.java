package behavioral.interpreter.support;

import behavioral.interpreter.AbstractNode;

/**
 * And 解释：非终结符表达式
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class AndNode extends AbstractNode {
    private AbstractNode left;
    private AbstractNode right;

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpret() {
        return left.interpret() + " 再 " + right.interpret();
    }
}
