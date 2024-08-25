package hochenchong.chapter.chapter18.client;

import hochenchong.protocol.resp.QuitGroupRespPacket;
import hochenchong.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
public class QuitGroupRespHandler extends SimpleChannelInboundHandler<QuitGroupRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRespPacket msg) throws Exception {
        // 如果加入失败，则是自己的消息
        if (!msg.isSuccess()) {
            System.err.println("退出群 [" + msg.getGroupId() + "] 失败，原因是：" + msg.getData());
            return;
        }
        // 成功的话，需要判断消息是否是自己
        String username = SessionUtils.getSession(ctx.channel()).getUsername();
        if (username.equals(msg.getData())) {
            System.out.println("退出群 [" + msg.getGroupId() + "] 成功！");
        } else {
            System.out.println(msg.getData() + " 退出群聊 [" + msg.getGroupId() + "]");
        }
    }
}
