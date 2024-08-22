package hochenchong.chapter8;


import hochenchong.chapter8.packet.LoginRequestPacket;
import hochenchong.chapter8.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author hochenchong
 * @date 2024/08/22
 */
class PacketCodeCTest {

    @Test
    void encodeAndDecode() {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(1);
        packet.setUsername("zhangsan");
        packet.setPassword("password");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ByteBufAllocator.DEFAULT, packet);
        Assertions.assertNotNull(byteBuf);

        Packet decode = packetCodeC.decode(byteBuf);
        Assertions.assertNotNull(decode);
        Assertions.assertEquals(Command.LOGIN_REQUEST, decode.getCommand());
    }
}