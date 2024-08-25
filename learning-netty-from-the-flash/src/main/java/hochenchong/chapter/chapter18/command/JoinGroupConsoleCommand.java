package hochenchong.chapter.chapter18.command;

import hochenchong.chapter.chapter17.command.ConsoleCommand;
import hochenchong.protocol.req.JoinGroupReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        JoinGroupReqPacket packet = new JoinGroupReqPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
