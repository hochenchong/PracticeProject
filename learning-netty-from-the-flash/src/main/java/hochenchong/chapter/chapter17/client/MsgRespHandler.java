package hochenchong.chapter.chapter17.client;

import hochenchong.protocol.resp.MsgRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/24
 */
public class MsgRespHandler extends SimpleChannelInboundHandler<MsgRespPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgRespPacket msg) throws Exception {
        System.out.println(msg.getFromUserId() + ":" + msg.getFromUsername() + " -> "
                + msg.getMessage());
    }
}
