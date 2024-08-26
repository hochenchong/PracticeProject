package hochenchong.chapter.chapter19.client;

import hochenchong.protocol.resp.GroupMsgRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/26
 */
public class GroupMsgRespHandler extends SimpleChannelInboundHandler<GroupMsgRespPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMsgRespPacket msg) throws Exception {
        System.out.println("收到群 [" + msg.getGroupId() + "] 中 [" + msg.getFromUser()
                + "] 发来的消息：" + msg.getMsg());
    }
}
