package hochenchong.chapter.chapter10;

import hochenchong.protocol.Packet;
import hochenchong.protocol.PacketCodeC;
import hochenchong.protocol.req.LoginReqPacket;
import hochenchong.protocol.req.MsgReqPacket;
import hochenchong.protocol.resp.LoginRespPacket;
import hochenchong.protocol.resp.MsgRespPacket;
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
        if (packet instanceof LoginReqPacket loginReqPacket) {
            // 登录请求
            LoginRespPacket responsePacket = new LoginRespPacket();
            loginReqPacket.setVersion(loginReqPacket.getVersion());
            if (valid(loginReqPacket)) {
                // 校验成功
                System.out.println(LocalDateTime.now() + " " + loginReqPacket.getUsername() + " 登录服务器！");
                responsePacket.setSuccess(true);
            } else {
                // 校验失败
                responsePacket.setSuccess(false);
                responsePacket.setReason("账号或密码校验失败！");
            }
            // 编码回包
            ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), responsePacket);
            ctx.channel().writeAndFlush(buf);
        } else if (packet instanceof MsgReqPacket msgReqPacket) {
            // 消息请求
            System.out.println(LocalDateTime.now() + "：收到客户端消息：" + msgReqPacket.getMessage());

            // 回复消息
            MsgRespPacket msgRespPacket = new MsgRespPacket();
            msgRespPacket.setMessage("服务端回复 【" + msgReqPacket.getMessage() + "】");
            ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), msgRespPacket);
            ctx.channel().writeAndFlush(buf);
        }
    }

    private boolean valid(LoginReqPacket loginReqPacket) {
        return true;
    }
}
