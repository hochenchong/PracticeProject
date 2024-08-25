package hochenchong.chapter.chapter17.server;

import hochenchong.chapter.chapter16.Session;
import hochenchong.protocol.req.CreateGroupReqPacket;
import hochenchong.protocol.resp.CreateGroupRespPacket;
import hochenchong.utils.IdUtils;
import hochenchong.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class CreateGroupReqHandler extends SimpleChannelInboundHandler<CreateGroupReqPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupReqPacket msg) throws Exception {
        List<String> userIds = msg.getUserIds();

        List<String> usernames = new ArrayList<>();
        // 创建一个 Channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 筛选待加入群聊的用户的 channel 和 username
        for (String userId : userIds) {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                usernames.add(SessionUtils.getSession(channel).getUsername());
            }
        }

        // 拉入的时候，加上自己的
        Session session = SessionUtils.getSession(ctx.channel());
        usernames.add(session.getUsername());
        channelGroup.add(ctx.channel());
        // 去重复
        usernames = usernames.stream().distinct().toList();

        // 响应结果
        CreateGroupRespPacket createGroupRespPacket = new CreateGroupRespPacket();
        createGroupRespPacket.setSuccess(true);
        createGroupRespPacket.setGroupId(IdUtils.getGroupId());
        createGroupRespPacket.setUsernames(usernames);

        // 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupRespPacket);

        System.out.print("群创建成功，群 id 为：[" + createGroupRespPacket.getGroupId() + "]，");
        System.out.println("群里面有：" + createGroupRespPacket.getUsernames());
    }
}
