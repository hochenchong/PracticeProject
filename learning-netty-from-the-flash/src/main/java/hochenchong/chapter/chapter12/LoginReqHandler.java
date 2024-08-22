package hochenchong.chapter.chapter12;

import hochenchong.protocol.req.LoginReqPacket;
import hochenchong.protocol.resp.LoginRespPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * 登录请求处理
 *
 * @author hochenchong
 * @date 2024/08/22
 */
public class LoginReqHandler extends SimpleChannelInboundHandler<LoginReqPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginReqPacket msg) throws Exception {
        // 登录逻辑
        LoginRespPacket loginRespPacket = login(msg);
        ctx.channel().writeAndFlush(loginRespPacket);
    }

    private LoginRespPacket login(LoginReqPacket loginReqPacket) {
        LoginRespPacket responsePacket = new LoginRespPacket();
        if (valid(loginReqPacket)) {
            // 校验成功
            System.out.println(LocalDateTime.now() + " " + loginReqPacket.getUsername() + " 登录服务器！");
            responsePacket.setSuccess(true);
        } else {
            // 校验失败
            responsePacket.setSuccess(false);
            responsePacket.setReason("账号或密码校验失败！");
        }
        return responsePacket;
    }

    private boolean valid(LoginReqPacket loginReqPacket) {
        return true;
    }
}
