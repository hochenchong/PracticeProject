package hochenchong.chapter8;

import hochenchong.chapter8.packet.LoginRequestPacket;
import hochenchong.chapter8.packet.Packet;
import hochenchong.chapter8.serialize.JSONSerializer;
import hochenchong.chapter8.serialize.Serializer;
import hochenchong.chapter8.serialize.SerializerAlgorithm;
import hochenchong.chapter9.resp.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

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



    /**
     * 编码
     *
     * @param byteBufAllocator byteBufAllocator
     * @param packet 数据
     * @return ByteBuf 对象
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        ByteBuf byteBuf = byteBufAllocator.buffer();
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

    private Class<? extends Packet> getRequestType(byte command) {
        if (Command.LOGIN_REQUEST == command) {
            return LoginRequestPacket.class;
        }
        if (Command.LOGIN_RESPONSE == command) {
            return LoginResponsePacket.class;
        }
        return null;
    }
}
