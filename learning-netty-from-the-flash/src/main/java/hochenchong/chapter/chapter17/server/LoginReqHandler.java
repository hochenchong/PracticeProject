package hochenchong.chapter.chapter17.server;

import hochenchong.chapter.chapter16.Session;
import hochenchong.protocol.req.LoginReqPacket;
import hochenchong.protocol.resp.LoginRespPacket;
import hochenchong.utils.SessionUtils;
import hochenchong.utils.IdUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/24
 */
public class LoginReqHandler extends SimpleChannelInboundHandler<LoginReqPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginReqPacket msg) throws Exception {
        LoginRespPacket loginRespPacket = new LoginRespPacket();
        String userId = IdUtils.getUserId();
        SessionUtils.bindSession(new Session(userId, msg.getUsername()), ctx.channel());

        loginRespPacket.setUserId(userId);
        loginRespPacket.setUsername(msg.getUsername());
        System.out.println("[" + msg.getUsername() + "] 登录成功");

        // 响应登录
        ctx.channel().writeAndFlush(loginRespPacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = SessionUtils.getSession(ctx.channel());
        if (session != null) {
            System.out.println("[" + session.getUsername() + "] 退出登录");
            SessionUtils.unBindSession(ctx.channel());
        }
        super.channelInactive(ctx);
    }
}
