package hochenchong.protocol;

import hochenchong.protocol.command.Command;
import hochenchong.protocol.req.*;
import hochenchong.protocol.resp.*;
import hochenchong.serialize.JSONSerializer;
import hochenchong.serialize.Serializer;
import hochenchong.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 编解码
 *     自定义通信协议
 *      魔数：4 字节
 *      版本号：1 字节
 *      序列化算法：1 字节
 *      指令：1 字节
 *      数据长度： 4 字节
 *      数据：N 字节
 *
 * @author hochenchong
 * @date 2024/08/22
 */
public class PacketCodeC {
    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();

    static {
        packetTypeMap.put(Command.LOGIN_REQ, LoginReqPacket.class);
        packetTypeMap.put(Command.LOGIN_RESP, LoginRespPacket.class);
        packetTypeMap.put(Command.MSG_REQ, MsgReqPacket.class);
        packetTypeMap.put(Command.MSG_RESP, MsgRespPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQ, CreateGroupReqPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESP, CreateGroupRespPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQ, JoinGroupReqPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESP, JoinGroupRespPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQ, QuitGroupReqPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESP, QuitGroupRespPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQ, ListGroupMembersReqPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESP, ListGroupMembersRespPacket.class);
        packetTypeMap.put(Command.GROUP_MSG_REQ, GroupMsgReqPacket.class);
        packetTypeMap.put(Command.GROUP_MSG_RESP, GroupMsgRespPacket.class);
    }

    /**
     * 编码
     *
     * @param byteBufAllocator byteBufAllocator
     * @param packet 数据
     * @return ByteBuf 对象
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        return encode(byteBufAllocator.buffer(), packet);
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 序列化对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 封装设计好的通信协议
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令信息
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        if (requestType == null) {
            return null;
        }
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (serializer == null) {
            return null;
        }
        return serializer.deserialize(requestType, bytes);
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        if (SerializerAlgorithm.JSON == serializeAlgorithm) {
            return new JSONSerializer();
        }
        return null;
    }

    private static Class<? extends Packet> getRequestType(Byte command) {
        return packetTypeMap.get(command);
    }
}
