package hochenchong.chapter.chapter19.server;

import hochenchong.protocol.req.GroupMsgReqPacket;
import hochenchong.protocol.resp.GroupMsgRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author hochenchong
 * @date 2024/08/26
 */
public class GroupMsgReqHandler extends SimpleChannelInboundHandler<GroupMsgReqPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMsgReqPacket msg) throws Exception {
        String groupId = msg.getGroupId();

        // 构造响应回包
        GroupMsgRespPacket packet = new GroupMsgRespPacket();
        packet.setGroupId(groupId);
        packet.setMsg(msg.getMsg());
        packet.setFromUser(SessionUtils.getSession(ctx.channel()));

        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        if (channelGroup != null && !channelGroup.isEmpty()) {
            channelGroup.writeAndFlush(packet);
        }
    }
}
