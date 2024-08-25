package hochenchong.chapter.chapter18.command;

import hochenchong.chapter.chapter17.command.ConsoleCommand;
import hochenchong.protocol.req.ListGroupMembersReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 查看群成员列表
 *
 * @author hochenchong
 * @date 2024/08/25
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入 groupId，查看群成员：");
        String groupId = scanner.next();

        ListGroupMembersReqPacket packet = new ListGroupMembersReqPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
