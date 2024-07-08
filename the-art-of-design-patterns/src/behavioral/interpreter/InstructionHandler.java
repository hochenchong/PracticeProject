package behavioral.interpreter;

import behavioral.interpreter.support.*;

import java.util.Stack;

/**
 * 指令处理类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class InstructionHandler {
    private AbstractNode node;

    public void handle(String instruction) {
        AbstractNode left, right, direction, action, distance;
        Stack<AbstractNode> stack = new Stack();
        // 以空格分割指令字符串
        String[] words = instruction.split(" ");
        for (int i = 0; i < words.length; i++) {
            // 本实例采用栈方式处理指令，如果遇到 and，后面 3 个单词作为 3 个终结符表达式连成一个简单橘子
            // SentenceNode 作为 and 的右表达式，而将从栈顶弹出的表达式作为 and 的左表达式，最后将新的 and 表达式压入栈中
            if ("and".equalsIgnoreCase(words[i])) {
                // 弹出栈顶表达式作为左表达式
                left = stack.pop();
                String word1 = words[++i];
                direction = new DirectionNode(word1);
                String word2 = words[++i];
                action = new ActionNode(word2);
                String word3 = words[++i];
                distance = new DistanceNode(word3);
                right = new SentenceNode(direction, action, distance);
                stack.push(new AndNode(left, right));
            } else {
                // 从头开始解释，则将前 3 个单词组成一个简单句子，然后压入栈中
                String word1 = words[i];
                direction = new DirectionNode(word1);
                String word2 = words[++i];
                action = new ActionNode(word2);
                String word3 = words[++i];
                distance = new DistanceNode(word3);
                left = new SentenceNode(direction, action, distance);
                stack.push(left);
            }
        }
        // 将全部表达式从栈中弹出
        this.node = stack.pop();
    }

    // 解释表达式
    public String output() {
        return node.interpret();
    }
}
