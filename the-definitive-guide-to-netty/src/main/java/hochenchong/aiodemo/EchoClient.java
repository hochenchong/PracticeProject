package hochenchong.aiodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // 创建一个异步客户端通道
            final AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();
            InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8080);
            
            // 连接到服务器
            clientChannel.connect(hostAddress, null, new CompletionHandler<Void, Void>() {
                @Override
                public void completed(Void result, Void attachment) {
                    // 发送消息到服务器
                    ByteBuffer buffer = ByteBuffer.wrap("Hello, Server!".getBytes());
                    clientChannel.write(buffer, buffer, new CompletionHandler<>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.clear();
                            clientChannel.read(buffer, buffer, new CompletionHandler<>() {
                                @Override
                                public void completed(Integer result, ByteBuffer buffer) {
                                    buffer.flip();
                                    byte[] bytes = new byte[buffer.remaining()];
                                    buffer.get(bytes);
                                    System.out.println("Received from server: " + new String(bytes));
                                    try {
                                        clientChannel.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void failed(Throwable exc, ByteBuffer buffer) {
                                    System.out.println("Failed to read from server.");
                                    exc.printStackTrace();
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
                            System.out.println("Failed to write to server.");
                            exc.printStackTrace();
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
                    System.out.println("Failed to connect to server.");
                    exc.printStackTrace();
                }
            });

            // 主线程保持运行
            Thread.sleep(5000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
