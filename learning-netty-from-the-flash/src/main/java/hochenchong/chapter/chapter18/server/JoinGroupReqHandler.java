package hochenchong.chapter.chapter18.server;

import hochenchong.protocol.req.JoinGroupReqPacket;
import hochenchong.protocol.resp.JoinGroupRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class JoinGroupReqHandler extends SimpleChannelInboundHandler<JoinGroupReqPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupReqPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        JoinGroupRespPacket packet = new JoinGroupRespPacket();
        packet.setGroupId(groupId);

        // 获取群组
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        if (channelGroup == null) {
            packet.setSuccess(false);
            packet.setData("群组不存在");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        if (channelGroup.contains(ctx.channel())) {
            packet.setSuccess(false);
            packet.setData("已在该群组，请勿重复加入");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        String username = SessionUtils.getSession(ctx.channel()).getUsername();
        packet.setSuccess(true);
        packet.setData(username);
        channelGroup.add(ctx.channel());
        System.out.println(username + " 加入群组 [" + groupId + "]");
        // 通知所有人
        channelGroup.writeAndFlush(packet);
    }
}
