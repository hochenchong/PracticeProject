package hochenchong.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞 I/O 的 TimeServer
 *
 * @author hochenchong
 * @date 2024/07/17
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            // 没捕获异常以及，没对端口范围进行校验
            port = Integer.parseInt(args[0]);
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("The time server is start in port:" + port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            // 未处理
        }
    }
}
