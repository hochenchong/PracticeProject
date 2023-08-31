package hochenchong.chapter8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;

/**
 * 代码清单 8-9：优雅关闭
 */
public class GracefulShutdown {
    public static void main(String[] args) {
        GracefulShutdown client = new GracefulShutdown();
        client.boostrap();

    }

    public void boostrap() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                // ...
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received data");
                    }
                });
        bootstrap.connect(new InetSocketAddress("www.manning", 80))
                .syncUninterruptibly();
        // 优雅关闭
        Future<?> future = group.shutdownGracefully();
        // 阻塞直到 group 关闭
        future.syncUninterruptibly();
    }
}
