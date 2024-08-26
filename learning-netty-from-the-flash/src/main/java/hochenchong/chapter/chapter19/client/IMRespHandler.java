package hochenchong.chapter.chapter19.client;

import hochenchong.chapter.chapter17.client.CreateGroupRespHandler;
import hochenchong.chapter.chapter17.client.MsgRespHandler;
import hochenchong.chapter.chapter18.client.JoinGroupRespHandler;
import hochenchong.chapter.chapter18.client.QuitGroupRespHandler;
import hochenchong.chapter.chapter18.client.ListGroupMembersRespHandler;
import hochenchong.protocol.Packet;
import hochenchong.protocol.command.Command;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hochenchong
 * @date 2024/08/26
 */
@ChannelHandler.Sharable
public class IMRespHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMRespHandler INSTANCE = new IMRespHandler();

    private final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    public IMRespHandler() {
        handlerMap = new HashMap<>();

        // 这里的 handler 都可以改为单例
        handlerMap.put(Command.MSG_RESP, new MsgRespHandler());
        handlerMap.put(Command.CREATE_GROUP_RESP, new CreateGroupRespHandler());
        handlerMap.put(Command.JOIN_GROUP_RESP, new JoinGroupRespHandler());
        handlerMap.put(Command.QUIT_GROUP_RESP, new QuitGroupRespHandler());
        handlerMap.put(Command.LIST_GROUP_MEMBERS_RESP, new ListGroupMembersRespHandler());
        handlerMap.put(Command.GROUP_MSG_RESP, new GroupMsgRespHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        SimpleChannelInboundHandler<? extends Packet> handler = handlerMap.get(msg.getCommand());
        if (handler != null) {
            handler.channelRead(ctx, msg);
        }
    }
}
