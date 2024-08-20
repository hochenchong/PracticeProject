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
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    // 连接建立成功之后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now() + "：客户端写出数据！");
        // 获取数据
        ByteBuf buf = getByteBuf(ctx);
        // 写数据
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(LocalDateTime.now() + "：客户端读到数据 -> " + buf.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "hello world!".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
