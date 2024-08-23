package hochenchong.chapter.chapter12;

import hochenchong.protocol.req.LoginReqPacket;
import hochenchong.protocol.resp.LoginRespPacket;
import hochenchong.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * 登录响应处理
 *
 * @author hochenchong
 * @date 2024/08/22
 */
public class LoginRespHandler extends SimpleChannelInboundHandler<LoginRespPacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 构造登录信息
        LoginReqPacket packet = new LoginReqPacket();
        packet.setUserId(1);
        packet.setUsername("zhangsan");
        packet.setPassword("password");
        // 写数据
        ctx.channel().writeAndFlush(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRespPacket loginRespPacket) throws Exception {
        if (loginRespPacket.isSuccess()) {
            // 校验成功
            LoginUtils.markAsLogin(ctx.channel());
            System.out.println(LocalDateTime.now() + " 客户端登录成功！");
        } else {
            // 校验失败
            System.out.println(LocalDateTime.now() + " 客户端登录失败！原因：" + loginRespPacket.getReason());
        }
    }
}
