package hochenchong.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 代码清单 9-6 测试 FrameChunkDecoder
 */
public class FrameChunkDecoderTest {

    @Test
    void decode() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));
        assertTrue(channel.writeInbound(input.readBytes(2)));

        try {
            channel.writeInbound(input.readBytes(4));
            // 如果上面没报错，则到达该断言则测试失败
            fail();
        } catch (TooLongFrameException e) {
            // 处理异常
        }
        assertTrue(channel.writeInbound(input.readBytes(3)));
        assertTrue(channel.finish());

        // 读取数据
        ByteBuf read = channel.readInbound();
        assertEquals(buf.readSlice(2), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.skipBytes(4).readSlice(3), read);
        read.release();
    }
}