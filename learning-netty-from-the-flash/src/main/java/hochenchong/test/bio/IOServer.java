package hochenchong.test.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hochenchong
 * @date 2024/08/20
 */
public class IOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            try {
                // 阻塞获取新连接
                Socket socket = serverSocket.accept();
                // 每个新连接创建一个新线程负责读取数据
                new Thread(() -> {
                    int len;
                    byte[] data = new byte[1024];
                    try {
                        InputStream inputStream = socket.getInputStream();
                        while ((len = inputStream.read(data)) != -1) {
                            System.out.println(new String(data, 0, len));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } catch (IOException e) {
            }
        }
    }
}
