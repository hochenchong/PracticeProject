package hochenchong.chapter.chapter17.client;

import hochenchong.chapter.chapter16.Session;
import hochenchong.protocol.resp.LoginRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/24
 */
public class LoginRespHandler extends SimpleChannelInboundHandler<LoginRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRespPacket msg) throws Exception {
        SessionUtils.bindSession(new Session(msg.getUserId(), msg.getUsername()), ctx.channel());
        System.out.println("[" + msg.getUsername() + "]登录成功，userId 为：" + msg.getUserId());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
    }
}
