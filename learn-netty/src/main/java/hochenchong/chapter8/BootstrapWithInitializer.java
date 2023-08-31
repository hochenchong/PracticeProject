package hochenchong.chapter8;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

import java.net.InetSocketAddress;

/**
 * 代码清单 8-6：引导和使用 ChannelInitializer
 */
public class BootstrapWithInitializer {
    public void bootstrap() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<Channel>() {
                // 注册一个 ChannelInitializer 实例来设置 ChannelPipeline
                // 书中实例是定义了 ChannelInitializerImpl 类
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new HttpClientCodec());
                    pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
                }
            });
        // 绑定端口
        ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(8080));
        future.sync();
    }

    // 用以设置 ChannelPipeline 的自定义 ChannelInitializerImpl 实现
    final class ChannelInitializerImpl extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new HttpClientCodec());
            pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }
    }
}
