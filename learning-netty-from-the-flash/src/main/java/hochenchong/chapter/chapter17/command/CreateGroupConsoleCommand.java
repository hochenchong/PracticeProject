package hochenchong.chapter.chapter17.command;

import hochenchong.protocol.req.CreateGroupReqPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_SPLIT = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入 userId 列表，以英文逗号隔开，拉入群聊：");
        String userIds = scanner.next();
        if (userIds == null || userIds.trim().isEmpty()) {
            System.out.println("无效的输入");
            return;
        }
        String[] split = userIds.split(USER_ID_SPLIT);
        CreateGroupReqPacket createGroupReqPacket = new CreateGroupReqPacket();
        createGroupReqPacket.setUserIds(Arrays.asList(split));

        channel.writeAndFlush(createGroupReqPacket);
    }
}
