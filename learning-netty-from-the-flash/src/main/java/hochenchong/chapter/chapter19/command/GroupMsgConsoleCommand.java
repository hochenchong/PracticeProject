package hochenchong.chapter.chapter19.command;

import hochenchong.chapter.chapter17.command.ConsoleCommand;
import hochenchong.protocol.req.GroupMsgReqPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author hochenchong
 * @date 2024/08/26
 */
public class GroupMsgConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入 groupId，发送群消息：");
        String groupId = scanner.next();
        String msg = scanner.next();

        GroupMsgReqPacket packet = new GroupMsgReqPacket();
        packet.setGroupId(groupId);
        packet.setMsg(msg);
        channel.writeAndFlush(packet);
    }
}
