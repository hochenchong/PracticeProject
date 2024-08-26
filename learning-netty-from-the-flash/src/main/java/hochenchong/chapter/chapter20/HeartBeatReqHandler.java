package hochenchong.chapter.chapter20;

import hochenchong.protocol.req.HeartBeatReqPacket;
import hochenchong.protocol.resp.HeartBeatRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/26
 */
public class HeartBeatReqHandler extends SimpleChannelInboundHandler<HeartBeatReqPacket> {
    public static final HeartBeatReqHandler INSTANCE = new HeartBeatReqHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatReqPacket msg) throws Exception {
        ctx.writeAndFlush(new HeartBeatRespPacket());
    }
}
