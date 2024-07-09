package behavioral.interpreter.support;

import behavioral.interpreter.AbstractNode;

/**
 * 动作解释：终结符表达式
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class ActionNode extends AbstractNode {
    private String action;

    public ActionNode(String action) {
        this.action = action;
    }

    @Override
    public String interpret() {
        if ("move".equalsIgnoreCase(action)) {
            return "移动";
        }
        if ("run".equalsIgnoreCase(action)) {
            return "快速移动";
        }
        return "无效指令";
    }
}
