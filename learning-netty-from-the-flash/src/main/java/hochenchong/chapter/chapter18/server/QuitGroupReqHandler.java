package hochenchong.chapter.chapter18.server;

import hochenchong.protocol.req.QuitGroupReqPacket;
import hochenchong.protocol.resp.QuitGroupRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class QuitGroupReqHandler extends SimpleChannelInboundHandler<QuitGroupReqPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupReqPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        QuitGroupRespPacket packet = new QuitGroupRespPacket();
        packet.setGroupId(groupId);

        // 获取群组
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        if (channelGroup == null) {
            packet.setSuccess(false);
            packet.setData("群组不存在");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        if (!channelGroup.contains(ctx.channel())) {
            packet.setSuccess(false);
            packet.setData("不在该群组");
            ctx.channel().writeAndFlush(packet);
            return;
        }
        String username = SessionUtils.getSession(ctx.channel()).getUsername();
        packet.setSuccess(true);
        packet.setData(username);
        channelGroup.add(ctx.channel());
        System.out.println(username + " 退出群组 [" + groupId + "]");
        // 通知所有人
        channelGroup.writeAndFlush(packet);
        SessionUtils.quitChannelGroup(groupId, ctx.channel());
    }
}
