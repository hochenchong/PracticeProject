package hochenchong.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hochenchong
 * @date 2024/07/17
 */
public class ReadCompletionhandler implements CompletionHandler<Integer, ByteBuffer> {
    private final AsynchronousSocketChannel channel;

    public ReadCompletionhandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        String req = new String(body, StandardCharsets.UTF_8);
        System.out.println("The time server receive order : " + req);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "BAD ORDER";
        doWrite(currentTime);
    }

    private void doWrite(String currentTime) {
        if (currentTime == null || currentTime.trim().isEmpty()) {
            return;
        }
        byte[] bytes = currentTime.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        channel.write(byteBuffer, byteBuffer, new CompletionHandler<>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                // 如果没发送完成，继续发送
                if (attachment.hasRemaining()) {
                    channel.write(attachment, attachment, this);
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
