package hochenchong.chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 代码清单 1-3 异步地建立连接
 *
 * 代码清单 1-4 回调实战
 */
public class ConnectExample {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
    public static void connect() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        ChannelFuture future = channel.connect(new InetSocketAddress("192.168.0.1", 25));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                // 检查操作的状态
                if (future.isSuccess()) {
                    // 操作成功，创建一个 ByteBuf 以持有数据
                    ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                    // 将数据异步发送到远程节点，返回一个 ChannelFuture
                    ChannelFuture wf = future.channel().writeAndFlush(buffer);
                    // ...
                } else {
                    // 如果报错，则访问描述原因的 Throwable
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }
}
