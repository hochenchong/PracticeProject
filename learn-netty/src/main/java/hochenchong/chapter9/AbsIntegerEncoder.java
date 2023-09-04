package hochenchong.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 代码清单9-3 AbsIntegerEncoder
 * 整型绝对值编码器
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        // 检查是否有足够的字节用来编码
        while (msg.readableBytes() >= 4) {
            int abs = Math.abs(msg.readInt());
            out.add(abs);
        }
    }
}
