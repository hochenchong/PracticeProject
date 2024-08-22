package hochenchong.chapter.chapter12;

import hochenchong.protocol.resp.MsgRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * 消息响应处理
 *
 * @author hochenchong
 * @date 2024/08/22
 */
public class MsgRespHandler extends SimpleChannelInboundHandler<MsgRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgRespPacket msgRespPacket) throws Exception {
        System.out.println(LocalDateTime.now() + "：收到服务器消息：" + msgRespPacket.getMessage());
    }
}
