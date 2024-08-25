package hochenchong.chapter.chapter18.client;

import hochenchong.chapter.chapter16.Session;
import hochenchong.protocol.req.ListGroupMembersReqPacket;
import hochenchong.protocol.resp.ListGroupMembersRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class ListGroupMembersReqHandler extends SimpleChannelInboundHandler<ListGroupMembersReqPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersReqPacket msg) throws Exception {
        ListGroupMembersRespPacket packet = new ListGroupMembersRespPacket();
        packet.setGroupId(msg.getGroupId());

        // 获取群 ChannelGroup
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(msg.getGroupId());
        if (channelGroup == null) {
            packet.setSessionList(null);
            ctx.channel().writeAndFlush(packet);
            return;
        }
        // 遍历群成员对应的 Session 构造成员信息
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            sessionList.add(SessionUtils.getSession(channel));
        }
        packet.setSessionList(sessionList);
        ctx.channel().writeAndFlush(packet);
    }
}
