package hochenchong.chapter.chapter15;

import hochenchong.chapter.chapter12.LoginReqHandler;
import hochenchong.chapter.chapter12.MsgReqHandler;
import hochenchong.chapter.chapter12.PacketDecoder;
import hochenchong.chapter.chapter12.PacketEncoder;
import hochenchong.chapter.chapter13.CustomLengthFieldBasedFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author hochenchong
 * @date 2024/08/23
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                // 服务端设置一些 TCP 参数，最常见是 SO_BACKLOG，系统用于临时存放已完成三次握手的请求队列最大长度。
                // 如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大该参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 开启 TCP 底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 是否开启 Nagle 算法，true 表示关闭，false 开启。
                // 如果要求高实时性，有数据发送时马上发送，就设置为关闭；减少发送次数，减少网络交互，就设置开启
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 用于指定服务端启动过程中的逻辑，通常用不到
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("服务端启动中");
                    }
                })
                // 用于指定处理新连接数据的读写处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        // 自定义 LengthFieldBasedFrameDecoder，拒接非本协议链接
                        ch.pipeline().addLast(new CustomLengthFieldBasedFrameDecoder());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginReqHandler());
                        // 用户认证 handler
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MsgReqHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(bootstrap,8000);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功！");
            } else {
                System.out.println("端口[" + port + "]绑定失败！");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
