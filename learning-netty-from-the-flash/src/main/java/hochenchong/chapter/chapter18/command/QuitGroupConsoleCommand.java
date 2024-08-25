package hochenchong.chapter.chapter18.command;

import hochenchong.chapter.chapter17.command.ConsoleCommand;
import hochenchong.protocol.req.JoinGroupReqPacket;
import hochenchong.protocol.req.QuitGroupReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 退出群聊
 *
 * @author hochenchong
 * @date 2024/08/25
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        QuitGroupReqPacket packet = new QuitGroupReqPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
