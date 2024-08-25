package hochenchong.chapter.chapter17.client;

import hochenchong.chapter.chapter16.Session;
import hochenchong.protocol.resp.CreateGroupRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class CreateGroupRespHandler extends SimpleChannelInboundHandler<CreateGroupRespPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRespPacket msg) throws Exception {
        Session session = SessionUtils.getSession(ctx.channel());
        System.out.print("群创建成功，群 id 为：[" + msg.getGroupId() + "]，");
        // 群成员里，移除掉自己的名称
        System.out.println("群里面有：" + msg.getUsernames().stream().filter(u -> !session.getUsername().equals(u)).toList());
    }
}