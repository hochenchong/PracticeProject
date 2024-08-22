package hochenchong.chapter.chapter13;

import hochenchong.chapter.chapter12.LoginRespHandler;
import hochenchong.chapter.chapter12.MsgRespHandler;
import hochenchong.chapter.chapter12.PacketDecoder;
import hochenchong.chapter.chapter12.PacketEncoder;
import hochenchong.protocol.PacketCodeC;
import hochenchong.protocol.req.MsgReqPacket;
import hochenchong.utils.LoginUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author hochenchong
 * @date 2024/08/22
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap
                // 指定线程模型
                .group(group)
                // 指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // IO 处理逻辑
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new CustomLengthFieldBasedFrameDecoder());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRespHandler());
                        ch.pipeline().addLast(new MsgRespHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        // 建立连接
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, final int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接服务器成功！");
                Channel channel = ((ChannelFuture) future).channel();
                // 连接成功后，启动控制台线程
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.println("重试次数已用完，放弃连接！");
                bootstrap.config().group().shutdownGracefully();
            } else {
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔，根据次数以 2 的幂次来建立连接
                int delay = 1 << order;
                System.out.println(LocalDateTime.now() + "：连接失败，第 " + order + " 次重连...");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtils.hasLogin(channel)) {
                    System.out.println("输入消息发送到服务器：");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    // 封装请求体
                    MsgReqPacket msgReqPacket = new MsgReqPacket();
                    msgReqPacket.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), msgReqPacket);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
