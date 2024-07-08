package behavioral.interpreter;

/**
 * 解释器模式客户端
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class InterpreterClient {
    public static void main(String[] args) {
        String instruction = "up move 5 and down run 10 and left move 5";
        InstructionHandler handler = new InstructionHandler();
        handler.handle(instruction);
        String outString = handler.output();
        System.out.println(outString);
    }
}
