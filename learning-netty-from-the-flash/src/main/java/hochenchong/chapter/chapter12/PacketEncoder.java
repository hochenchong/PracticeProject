package hochenchong.chapter.chapter12;

import hochenchong.protocol.Packet;
import hochenchong.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码逻辑
 *
 * @author hochenchong
 * @date 2024/08/22
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
    }
}
