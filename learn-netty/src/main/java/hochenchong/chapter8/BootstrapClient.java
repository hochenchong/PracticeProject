package hochenchong.chapter8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class BootstrapClient {

    public void bootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个 Bootstrap 类的实例以创建和连接新的客户端 Channel
        Bootstrap bootstrap = new Bootstrap();
        // 设置 EventLoopGroup，提供用于处理 Channel 事件的 EventLoop
        bootstrap.group(group)
                // 指定要使用的 Channel 实现
                .channel(NioSocketChannel.class)
                // 设置用于 Channel 事件和数据的 ChannelInboundHandler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0( ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                    }
                });
        // 连接到远程主机
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Connection established");
                } else {
                    System.err.println("Connection attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
