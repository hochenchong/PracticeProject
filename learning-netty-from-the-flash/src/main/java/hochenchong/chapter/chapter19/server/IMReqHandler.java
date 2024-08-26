package hochenchong.chapter.chapter19.server;

import hochenchong.chapter.chapter17.server.CreateGroupReqHandler;
import hochenchong.chapter.chapter17.server.MsgReqHandler;
import hochenchong.chapter.chapter18.server.ListGroupMembersReqHandler;
import hochenchong.chapter.chapter18.server.JoinGroupReqHandler;
import hochenchong.chapter.chapter18.server.QuitGroupReqHandler;
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
public class IMReqHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMReqHandler INSTANCE = new IMReqHandler();

    private final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    public IMReqHandler() {
        handlerMap = new HashMap<>();

        // 这里的 handler 都可以改为单例
        handlerMap.put(Command.MSG_REQ, new MsgReqHandler());
        handlerMap.put(Command.CREATE_GROUP_REQ, new CreateGroupReqHandler());
        handlerMap.put(Command.JOIN_GROUP_REQ, new JoinGroupReqHandler());
        handlerMap.put(Command.QUIT_GROUP_REQ, new QuitGroupReqHandler());
        handlerMap.put(Command.LIST_GROUP_MEMBERS_REQ, new ListGroupMembersReqHandler());
        handlerMap.put(Command.GROUP_MSG_REQ, new GroupMsgReqHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        SimpleChannelInboundHandler<? extends Packet> handler = handlerMap.get(msg.getCommand());
        if (handler != null) {
            handler.channelRead(ctx, msg);
        }
    }
}
