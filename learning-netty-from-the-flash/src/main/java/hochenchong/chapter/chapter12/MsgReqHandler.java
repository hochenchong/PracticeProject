package hochenchong.chapter.chapter12;

import hochenchong.protocol.req.MsgReqPacket;
import hochenchong.protocol.resp.MsgRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * 消息请求 Handler
 *
 * @author hochenchong
 * @date 2024/08/22
 */
public class MsgReqHandler extends SimpleChannelInboundHandler<MsgReqPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgReqPacket msg) throws Exception {
        // 消息请求
        System.out.println(LocalDateTime.now() + "：收到客户端消息：" + msg.getMessage());

        // 回复消息
        MsgRespPacket msgRespPacket = new MsgRespPacket();
        msgRespPacket.setMessage("服务端回复 【" + msg.getMessage() + "】");
        ctx.channel().writeAndFlush(msgRespPacket);
    }
}
