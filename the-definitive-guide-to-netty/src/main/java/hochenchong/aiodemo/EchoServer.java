package hochenchong.aiodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class EchoServer {
    public static void main(String[] args) {
        try {
            // 创建一个异步服务器通道并绑定到指定端口
            final AsynchronousServerSocketChannel serverChannel =
                    AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));

            // 接受客户端连接
            serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                @Override
                public void completed(AsynchronousSocketChannel clientChannel, Void attachment) {
                    // 再次调用 accept 方法来接受其他连接
                    serverChannel.accept(null, this);

                    // 处理客户端连接
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    clientChannel.read(buffer, buffer, new CompletionHandler<>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.flip();
                            clientChannel.write(buffer, buffer, new CompletionHandler<>() {
                                @Override
                                public void completed(Integer result, ByteBuffer buffer) {
                                    buffer.clear();
                                    clientChannel.read(buffer, buffer, this);
                                }

                                @Override
                                public void failed(Throwable exc, ByteBuffer buffer) {
                                    try {
                                        clientChannel.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer buffer) {
                            try {
                                clientChannel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    System.out.println("Failed to accept a connection.");
                    exc.printStackTrace();
                }
            });

            // 主线程保持运行
            System.out.println("Server is listening on port 8080...");
            while (true) {
                Thread.sleep(1000);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
