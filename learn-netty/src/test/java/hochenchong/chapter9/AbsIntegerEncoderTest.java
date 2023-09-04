package hochenchong.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 代码清单 9-4 测试 AbsIntegerEncoder
 */
public class AbsIntegerEncoderTest {

    @Test
    void encode() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * -1);
        }
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        // 写入 ByteBuf，并判断是否有数据可以读取
        assertTrue(channel.writeOutbound(buf));
        // 将该 Channel 标记为已完成状态
        assertTrue(channel.finish());

        // 读取数据，判断是否都是绝对值
        for (int i = 1; i < 10; i++) {
            assertEquals(i, (Integer) channel.readOutbound());
        }
        assertNull(channel.readOutbound());
    }
}