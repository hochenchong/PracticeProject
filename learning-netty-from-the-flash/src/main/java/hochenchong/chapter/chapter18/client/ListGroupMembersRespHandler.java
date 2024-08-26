package hochenchong.chapter.chapter18.client;

import hochenchong.protocol.resp.ListGroupMembersRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class ListGroupMembersRespHandler extends SimpleChannelInboundHandler<ListGroupMembersRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRespPacket msg) throws Exception {
        if (msg.getSessionList() == null) {
            System.err.println("群 [" + msg.getGroupId() + "] 不存在");
        } else {
            System.out.println("群 [" + msg.getGroupId() + "] 中的人包括：" + msg.getSessionList());
        }
    }
}
