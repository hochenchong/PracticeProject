package hochenchong.chapter6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author hochenchong
 * @date 2024/08/20
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuf = (ByteBuf) msg;
        System.out.println(LocalDateTime.now() + "：服务端读到数据 -> " + inBuf.toString(StandardCharsets.UTF_8));

        ByteBuf outBuf = getByteBuf(ctx);
        ctx.channel().writeAndFlush(outBuf);
        System.out.println(LocalDateTime.now() + "：服务端写出数据！");
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "hello，客户端！".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
