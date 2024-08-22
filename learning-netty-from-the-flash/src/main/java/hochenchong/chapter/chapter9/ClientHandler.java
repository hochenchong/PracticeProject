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
public class ClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 连接成功后，发起登录请求
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 构造登录信息
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(1);
        packet.setUsername("zhangsan");
        packet.setPassword("password");

        // 编码
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), packet);

        // 写数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket loginResponsePacket) {
            if (loginResponsePacket.isSuccess()) {
                // 校验成功
                System.out.println(LocalDateTime.now() + " 客户端登录成功！");
            } else {
                // 校验失败
                System.out.println(LocalDateTime.now() + " 客户端登录失败！原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
