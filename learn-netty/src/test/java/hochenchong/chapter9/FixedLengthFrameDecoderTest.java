package hochenchong.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 代码清单 9-2：测试 FixedLengthFrameDecoder
 * 书中使用的是 Junit4，这里使用的是 Junit5
 */
public class FixedLengthFrameDecoderTest {

    @Test
    public void decode() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // 写入数据
        assertTrue(channel.writeInbound(input.retain()));
        // 标记 Channel 为已完成状态
        assertTrue(channel.finish());

        // 读取所生成的消息，并验证是否有 3帧（切片），其中每帧（切片）都为 3 字节
        ByteBuf read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        // 该方法将引用计数减 1，引用计数为 0，则重新分配该对象
        // 返回值：ture 如果且仅当引用计数变为 0 且该对象已被清空。
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();
    }

    @Test
    public void decode2() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // writeInbound 返回 false，因为没有一个完整的可供读取的帧
        assertFalse(channel.writeInbound(input.readBytes(2)));
        assertTrue(channel.writeInbound(input.readBytes(7)));
        // 标记 Channel 为已完成状态
        assertTrue(channel.finish());

        // 读取所生成的消息，并验证是否有 3帧（切片），其中每帧（切片）都为 3 字节
        ByteBuf read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        // 该方法将引用计数减 1，引用计数为 0，则重新分配该对象
        // 返回值：ture 如果且仅当引用计数变为 0 且该对象已被清空。
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();
    }

}