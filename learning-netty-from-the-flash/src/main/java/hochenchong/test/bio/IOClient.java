package hochenchong.test.bio;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author hochenchong
 * @date 2024/08/20
 */
public class IOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8000);
        while (true) {
            socket.getOutputStream().write((LocalDateTime.now() + ": hello world").getBytes(StandardCharsets.UTF_8));
            Thread.sleep(2000L);
        }
    }
}
