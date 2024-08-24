package hochenchong.chapter.chapter16;

import hochenchong.protocol.req.MsgReqPacket;
import hochenchong.protocol.resp.MsgRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/24
 */
public class MsgReqHandler extends SimpleChannelInboundHandler<MsgReqPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgReqPacket msg) throws Exception {
        // 消息发送方的消息
        Session session = SessionUtils.getSession(ctx.channel());

        // 构造发送出去的消息
        MsgRespPacket msgRespPacket = new MsgRespPacket();
        msgRespPacket.setFromUserId(session.getUserId());
        msgRespPacket.setFromUsername(session.getUsername());
        msgRespPacket.setMessage(msg.getMessage());

        // 获取接收方的 Channel
        Channel toUserChannel = SessionUtils.getChannel(msg.getToUserId());

        // 发送消息
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(msgRespPacket);
        } else {
            System.out.println("[" + msg.getToUserId() + "] 不在线，发送失败！");
        }
    }
}
