package hochenchong.chapter.chapter9;

import hochenchong.protocol.PacketCodeC;
import hochenchong.protocol.req.LoginRequestPacket;
import hochenchong.protocol.Packet;
import hochenchong.protocol.resp.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

/**
 * @author hochenchong
 * @date 2024/08/22
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 校验请求
        ByteBuf byteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket loginRequestPacket) {
            LoginResponsePacket responsePacket = new LoginResponsePacket();
            loginRequestPacket.setVersion(loginRequestPacket.getVersion());
            if (valid(loginRequestPacket)) {
                // 校验成功
                System.out.println(LocalDateTime.now() + " " + loginRequestPacket.getUsername() + " 登录服务器！");
                responsePacket.setSuccess(true);
            } else {
                // 校验失败
                responsePacket.setSuccess(false);
                responsePacket.setReason("账号或密码校验失败！");
            }
            // 编码回包
            ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), responsePacket);
            ctx.channel().writeAndFlush(buf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
