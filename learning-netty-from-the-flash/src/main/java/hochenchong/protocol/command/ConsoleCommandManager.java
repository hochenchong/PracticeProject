package hochenchong.protocol.command;

import hochenchong.chapter.chapter17.command.ConsoleCommand;
import hochenchong.chapter.chapter17.command.CreateGroupConsoleCommand;
import hochenchong.chapter.chapter17.command.LogoutConsoleCommand;
import hochenchong.chapter.chapter17.command.SendToUserConsoleCommand;
import hochenchong.chapter.chapter18.command.JoinGroupConsoleCommand;
import hochenchong.chapter.chapter18.command.ListGroupMembersConsoleCommand;
import hochenchong.chapter.chapter18.command.QuitGroupConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        this.consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        // 获取第一个指令
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand == null) {
            System.out.println("无法识别 [" + command + "] 指令，请重新输入！");
            return;
        }
        consoleCommand.exec(scanner, channel);
    }
}
