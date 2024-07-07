package behavioral.command;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * 命令模式客户端
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class CommandClient {
    public static void main(String[] args) {
        FBSettingWindow window = new FBSettingWindow("功能键设置");

        FunctionButton button1 = new FunctionButton("功能键1");
        FunctionButton button2 = new FunctionButton("功能键2");

        Command command1 = (Command) XMLUtil.getBean(XMLTagNameConstant.COMMAND1);
        Command command2 = (Command) XMLUtil.getBean(XMLTagNameConstant.COMMAND2);

        button1.setCommand(command1);
        button2.setCommand(command2);

        window.addFunctionButton(button1);
        window.addFunctionButton(button2);
        window.display();

        // 调用功能键的业务方法
        button1.onClick();
        button2.onClick();
    }
}
